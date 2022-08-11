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

import java.math.BigInteger;
import java.util.Iterator;

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
     * <p>
     * Both d and e must be non-zero.
     *
     * @param d
     * @param e
     * @param f
     * @return an iterator over all integer solutions (x, y)
     * @throws IllegalArgumentException if d or e are zero.
     */
    public static Iterator<XYPair> solve(int d, int e, int f) {
        if (d == 0 || e == 0) {
            throw new IllegalArgumentException("d and e should be non-zero.");
        }

        Eq reduced = reduce(d, e, f);

        if (reduced == null) {
            return new EmptyIterator<>();
        } else {
            return solveReduced(reduced);
        }
    }

    private static Eq reduce(int d, int e, int f) {
        int gcd = Utils.gcd(d, e);

        if (f % gcd != 0) {
            // No solutions, as d x + e y will always be a multiple of gcd for integer x and y
            return null;
        }

        return new Eq(d / gcd, e / gcd, f / gcd);
    }

    private static Iterator<XYPair> solveReduced(Eq eq) {
        XYPair solution = findAnySolution(eq);
        return new LinearIterator(eq, solution);
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

        // Results are in prevR (which is the gcd = 1), prevS, and prevT
        // Thus, d * prevS + e * prevT = 1
        // We want d * x + e * y + f = 0, so we need to multiply by -f
        long x = -eq.f * prevS;
        long y = -eq.f * prevT;

        return new XYPair(x, y);
    }

    private static class LinearIterator implements Iterator<XYPair> {

        private final BigInteger xStep, yStep;
        private BigInteger posX, negX, posY, negY;
        private boolean posNext = true;

        public LinearIterator(Eq eq, XYPair initialSolution) {
            this.posX = initialSolution.x;
            this.negX = initialSolution.x;
            this.xStep = BigInteger.valueOf(eq.e);
            this.posY = initialSolution.y;
            this.negY = initialSolution.y;
            this.yStep = BigInteger.valueOf(-eq.d);

            // Move the negative solution one step, so we don't return (x, y) twice
            stepNeg();
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public XYPair next() {
            XYPair solution;

            if (posNext) {
                solution = new XYPair(posX, posY);
                stepPos();
            } else {
                solution = new XYPair(negX, negY);
                stepNeg();
            }

            posNext = !posNext;
            return solution;
        }

        private void stepPos() {
            posX = posX.add(xStep);
            posY = posY.add(yStep);
        }

        private void stepNeg() {
            negX = negX.subtract(xStep);
            negY = negY.subtract(yStep);
        }

    }

    private static class Eq {

        int d, e, f;

        Eq(int d, int e, int f) {
            this.d = d;
            this.e = e;
            this.f = f;
        }
    }
}
