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

import com.github.mangara.diophantine.EmptyIterator;
import com.github.mangara.diophantine.LinearSolver;
import com.github.mangara.diophantine.MergedIterator;
import com.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
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
            // TODO: Ensure a is not 0 by swapping a and c
            throw new UnsupportedOperationException("Not implemented yet.");
        }
        
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
        
        List<Integer> Ti = UnaryCongruenceSolver.solveReduced(1, v.negate().intValueExact(), u.abs().intValueExact());
        
        if (Ti.isEmpty()) {
            return new EmptyIterator<>();
        }
        
        
        
        
        throw new UnsupportedOperationException("Not supported yet.");
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
}
