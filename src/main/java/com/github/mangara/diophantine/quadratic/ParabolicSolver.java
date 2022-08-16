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
package com.github.mangara.diophantine.quadratic;

import com.github.mangara.diophantine.iterators.EmptyIterator;
import com.github.mangara.diophantine.LinearSolver;
import com.github.mangara.diophantine.iterators.MergedIterator;
import com.github.mangara.diophantine.XYPair;
import com.github.mangara.diophantine.iterators.MappingIterator;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParabolicSolver {

    /**
     * Solves the quadratic Diophantine equation 
     * a x^2 + b xy + c y^2 + d x + e y + f = 0,
     * given that D = b^2 - 4ac = 0 and not all of a, b, and c are zero.
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(int a, int b, int c, int d, int e, int f) {
        if (a == 0) {
            // b^2 = 4ac = 0 implies both a and b are 0.
            // Since not all of a, b, and c are zero, c is non-zero, so we swap x and y
            Iterator<XYPair> yxSolutions = solveNonZeroA(c, b, a, e, d, f);
            return new MappingIterator<>(yxSolutions, (sol) -> new XYPair(sol.y, sol.x));
        } else {
            return solveNonZeroA(a, b, c, d, e, f);
        }
    }
    
    // Pre: D = 0, a != 0
    private static Iterator<XYPair> solveNonZeroA(int a, int b, int c, int d, int e, int f) {
        // Multiply by 4a to get
        //  (2ax + by)^2 + 4adx + 4aey + 4af = 0
        // Substitute t = 2ax + by, u = 2(bd - 2ae), and v = d^2 - 4af to get
        //  (t + d)^2 = uy + v
        
        BigInteger u = computeU(a, b, d, e);
        
        if (u.signum() == 0) {
            return solveSimple(a, b, c, d, e, f);
        } else {
            return solveGeneral(a, b, c, d, e, f);
        }
    }

    // Pre: D = 0, a != 0, u = 2(bd - 2ae) = 0
    private static Iterator<XYPair> solveSimple(int a, int b, int c, int d, int e, int f) {
        // With u = 0, we're now solving
        //  (t + d)^2 = v
        
        BigInteger v = computeV(a, d, f);
        
        if (v.signum() == 0) {
            // (t + d)^2 = 0  =>  t + d = 0  =>  2ax + by + d = 0
            return LinearSolver.solve(Math.multiplyExact(2, a), b, d);
        }
        
        if (v.signum() < 0) {
            // (t + d)^2 = v has no solutions for v < 0
            return new EmptyIterator<>();
        }
        
        BigInteger[] sqrtV = v.sqrtAndRemainder();
        if (sqrtV[1].signum() != 0) {
            // (t + d)^2 = v has no solutions for v not a perfect square
            return new EmptyIterator<>();
        }
        
        int g = Math.toIntExact(sqrtV[0].longValueExact());
        
        // (t + d)^2 = g^2  =>  t + d = +/- g  =>  2ax + by + d +/- g = 0
        Iterator<XYPair> negativeG = LinearSolver.solve(Math.multiplyExact(2, a), b, Math.subtractExact(d, g));
        Iterator<XYPair> positiveG = LinearSolver.solve(Math.multiplyExact(2, a), b, Math.addExact(d, g));
        
        return MergedIterator.merge(negativeG, positiveG);
    }

    // Pre: D = 0, a != 0, u = 2(bd - 2ae) != 0
    private static Iterator<XYPair> solveGeneral(int a, int b, int c, int d, int e, int f) {
        // With u != 0, we're still solving
        //  (t + d)^2 = uy + v
        // To do that, we first solve
        //  (t + d)^2 = v (mod |u|)
        
        BigInteger u = computeU(a, b, d, e);
        BigInteger v = computeV(a, d, f);
        
        List<Integer> SqrtVModU = UnaryCongruenceSolver.solveReduced(1, v.negate().intValueExact(), u.abs().intValueExact());
        
        if (SqrtVModU.isEmpty()) {
            return new EmptyIterator<>();
        }
        
        List<Iterator<XYPair>> familyIterators = new ArrayList<>();
        
        for (Integer Ti : SqrtVModU) {
            // t + d = T_i + uk satisfies (t + d)^2 = v (mod |u|) for every integer k
            // Substituting t + d = Ti + uk into (t + d)^2 = uy + v and solving for y yields
            //  y = (Ti^2 - v) / u + 2Tik + uk^2 = r + sk + uk^2
            // where r = (Ti^2 - v) / u and s = 2Ti
            
            BigInteger BigTi = BigInteger.valueOf(Ti);
            BigInteger r = BigTi.multiply(BigTi).subtract(v).divide(u); // r = (Ti^2 - v) / u
            BigInteger s = BigInteger.TWO.multiply(BigTi);              // s = 2Ti
            
            // Substituting y = r + sk + uk^2 into 2ax + by = t = T_i - d + uk yields
            //  2ax = Ti - d - br + (u - bs)k - buk^2
            //    x = (Ti - d - br) / 2a + ((u - bs) / 2a)k - (bu / 2a)k^2
            // If all three coefficients are integers, this yields an x and y for each k
            
            BigInteger c1 = BigTi.subtract(BigInteger.valueOf(d)).subtract(BigInteger.valueOf(b).multiply(r));
            BigInteger c2 = u.subtract(BigInteger.valueOf(b).multiply(s));
            BigInteger c3 = BigInteger.valueOf(b).multiply(u).negate();
            
            BigInteger doubleA = BigInteger.TWO.multiply(BigInteger.valueOf(a));
            
            if (c1.mod(doubleA.abs()) == BigInteger.ZERO && c2.mod(doubleA.abs()) == BigInteger.ZERO && c3.mod(doubleA.abs()) == BigInteger.ZERO) {
                familyIterators.add(new ParabolicIterator(c1.divide(doubleA), c2.divide(doubleA), c3.divide(doubleA), r, s, u));
            }
        }
        
        return MergedIterator.merge(familyIterators);
    }
    
    private static BigInteger computeU(int a, int b, int d, int e) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger B = BigInteger.valueOf(b);
        BigInteger D = BigInteger.valueOf(d);
        BigInteger E = BigInteger.valueOf(e);
        
        // u = 2(bd - 2ae)
        return BigInteger.TWO.multiply(B.multiply(D).subtract(BigInteger.TWO.multiply(A).multiply(E)));
    }
    
    private static BigInteger computeV(int a, int d, int f) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger D = BigInteger.valueOf(d);
        BigInteger F = BigInteger.valueOf(f);
        
        // v = d^2 - 4af
        return D.multiply(D).subtract(BigInteger.valueOf(4).multiply(A).multiply(F));
    }
    
    private static class ParabolicIterator implements Iterator<XYPair> {

        private final BigInteger c1, c2, c3, r, s, u;
        private BigInteger k;

        public ParabolicIterator(BigInteger c1, BigInteger c2, BigInteger c3, BigInteger r, BigInteger s, BigInteger u) {
            this.c1 = c1;
            this.c2 = c2;
            this.c3 = c3;
            this.r = r;
            this.s = s;
            this.u = u;
            
            k = BigInteger.ZERO;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public XYPair next() {
            XYPair result = new XYPair(x(k), y(k));
            
            if (k.signum() > 0) {
                k = k.negate();
            } else {
                k = k.negate().add(BigInteger.ONE);
            }
            
            return result;
        }

        private BigInteger x(BigInteger k) {
            // x = c1 + c2k - c3k^2
            return c1.add(c2.multiply(k)).add(c3.multiply(k).multiply(k));
        }

        private BigInteger y(BigInteger k) {
            // y = r + sk + uk^2
            return r.add(s.multiply(k)).add(u.multiply(k).multiply(k));
        }

    }
}
