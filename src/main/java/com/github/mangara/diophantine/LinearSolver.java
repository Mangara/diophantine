/*
 * Copyright 2022 Sander Verdonschot <sander.verdonschot at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mangara.diophantine;

import com.github.mangara.diophantine.iterators.EmptyIterator;
import com.github.mangara.diophantine.iterators.IntegerIterator;
import com.github.mangara.diophantine.iterators.MappingIterator;
import com.github.mangara.diophantine.iterators.XYIterator;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.function.Function;

/**
 * Solves linear Diophantine equations.
 * <p>
 * This class allows you to solve Diophantine equations of the form
 *
 * \[d x + e y + f = 0\]
 *
 * where both d and e are non-zero.
 *
 * The method is based on https://www.alpertron.com.ar/METHODS.HTM#Linear
 */
public class LinearSolver {

    /**
     * Solves the linear Diophantine equation d x + e y + f = 0.
     *
     * @param d
     * @param e
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(long d, long e, long f) {
        if (d == 0 && e == 0) {
            return solveTrivial(f);
        } else if (d == 0) {
            return solveSingle(e, f, true);
        } else if (e == 0) {
            return solveSingle(d, f, false);
        } else {
            return solveGeneral(d, e, f);
        }
    }

    private static Iterator<XYPair> solveTrivial(long f) {
        if (f != 0) {
            return new EmptyIterator<>();
        }
        
        return new XYIterator();
    }

    /**
     * Solves the linear Diophantine equations g x + f = 0 or g y + f = 0.
     * <p>
     * If arbitraryX is true, the equation solved is g y + f = 0 and x ranges
     * over all integers. Otherwise, it is g x + f = 0, with y ranging over all
     * integers.
     *
     * @param g
     * @param f
     * @param arbitraryX
     * @return an iterator over all integer solutions (x, y)
     */
    private static Iterator<XYPair> solveSingle(long g, long f, boolean arbitraryX) {
        if (f % g != 0) {
            return new EmptyIterator<>();
        }
        
        BigInteger val = BigInteger.valueOf(Math.negateExact(f) / g);
        
        Function<BigInteger, XYPair> map;
        if (arbitraryX) {
            map = k -> new XYPair(k, val);
        } else {
            map = k -> new XYPair(val, k);
        }
        
        return new MappingIterator<>(new IntegerIterator(), map);
    }

    // Pre: d != 0 && e != 0
    private static Iterator<XYPair> solveGeneral(long d, long e, long f) {
        Eq reduced = reduce(d, e, f);

        if (reduced == null) {
            return new EmptyIterator<>();
        } else {
            return solveReduced(reduced);
        }
    }

    private static Eq reduce(long d, long e, long f) {
        long gcd = Utils.gcd(d, e);

        if (f % gcd != 0) {
            // No solutions, as d x + e y will always be a multiple of gcd for integer x and y
            return null;
        }

        return new Eq(d / gcd, e / gcd, f / gcd);
    }

    // Pre: d != 0 && e != 0 && d and e are co-prime
    private static Iterator<XYPair> solveReduced(Eq eq) {
        XYPair solution = findAnySolution(eq);

        final BigInteger dx = BigInteger.valueOf(eq.e);
        final BigInteger dy = BigInteger.valueOf(-eq.d);

        return new MappingIterator<>(new IntegerIterator(),
                (k) -> new XYPair(
                        solution.x.add(dx.multiply(k)),
                        solution.y.add(dy.multiply(k))
                )
        );
    }

    private static XYPair findAnySolution(Eq eq) {
        // Run the extended Euclidean algorithm ( https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm )
        long prevR = eq.d;
        long r = eq.e;
        long prevS = 1;
        long s = 0;
        long prevT = 0;
        long t = 1;

        while (r != 0) {
            long quotient = prevR / r;
            long tempR = r;
            long tempS = s;
            long tempT = t;

            r = prevR - quotient * r;
            s = prevS - quotient * s;
            t = prevT - quotient * t;

            prevR = tempR;
            prevS = tempS;
            prevT = tempT;
        }

        // Results are in prevR (which is the gcd = +/- 1), prevS, and prevT
        // Thus, d * prevS + e * prevT = +/- 1
        // We want d * x + e * y = -f, so we need to multiply by f or -f, depending on the sign of prevR
        long factor = Math.multiplyExact(Math.negateExact(eq.f), prevR);
        long x = Math.multiplyExact(factor, prevS); // -/+ f * prevS
        long y = Math.multiplyExact(factor, prevT); // -/+ f * prevT

        return new XYPair(x, y);
    }

    private static class Eq {

        long d, e, f;

        Eq(long d, long e, long f) {
            this.d = d;
            this.e = e;
            this.f = f;
        }
    }
}
