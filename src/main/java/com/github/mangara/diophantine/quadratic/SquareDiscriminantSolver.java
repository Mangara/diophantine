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

import com.github.mangara.diophantine.LinearSolver;
import com.github.mangara.diophantine.Utils;
import com.github.mangara.diophantine.XYPair;
import com.github.mangara.diophantine.iterators.EmptyIterator;
import com.github.mangara.diophantine.iterators.IntegerIterator;
import com.github.mangara.diophantine.iterators.MappingIterator;
import com.github.mangara.diophantine.iterators.MergedIterator;
import java.math.BigInteger;
import java.util.Iterator;

public class SquareDiscriminantSolver {

    /**
     * Solves the quadratic Diophantine equation 
     * a x^2 + b xy + c y^2 + d x + e y + f = 0,
     * given that D = b^2 - 4ac = g^2, for g > 0.
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
            return solveZeroA(a, b, c, d, e, f);
        } else {
            return solveNonZeroA(a, b, c, d, e, f);
        }
    }

    // Pre: D = b^2 - 4ac = g^2, g > 0 && a == 0
    private static Iterator<XYPair> solveZeroA(int a, int b, int c, int d, int e, int f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Pre: D = b^2 - 4ac = g^2, g > 0 && a != 0
    private static Iterator<XYPair> solveNonZeroA(int a, int b, int c, int d, int e, int f) {
        // Legendre's transformation with Dx = X + alpha and Dy = Y + beta gives
        //  aX^2 + bXY + cY^2 = k
        // After multiplying by 4a, we can rewrite this as
        //  (2aX + (b + g)Y)(2aX + (b - g)Y) = 4ak
        
        long D = Utils.discriminant(a, b, c);
        long g = Math.round(Math.sqrt(D));
        long k = Utils.legendreConstant(a, b, c, d, e, f, D);
        long fourAK = Math.multiplyExact(4L * a, k);
        
        long g1 = Utils.gcd(Math.multiplyExact(2L, a), Math.addExact(b, g));
        long g2 = Utils.gcd(Math.multiplyExact(2L, a), Math.subtractExact(b, g));
        long g1g2 = Math.multiplyExact(g1, g2);
        
        if (fourAK % g1g2 != 0) {
            // I haven't been able to find an example that hits this case.
            // It is possible that g1g2 always divides 4ak.
            return new EmptyIterator<>();
        }
        
        // This reduces to
        //  ((2a / g1)X + ((b + g) / g1)Y)((2a / g2)X + ((b - g) / g2)Y) = 4ak / g1g2
        
        if (k == 0) {
            // If k = 0 we can solve both parts separately
            //  2aX + (b + g)Y = 0  or  2aX + (b - g)Y = 0
            // Subsituting X = Dx - alpha and Y = Dy - beta gives
            //  2aDx + (b + g)Dy = 2a alpha + (b + g) beta  or
            //  2aDx + (b - g)Dy = 2a alpha + (b - g) beta
            
            long alpha = Utils.legendreAlpha(b, c, d, e);
            long beta = Utils.legendreBeta(a, b, d, e);
            
            long Dg1 = Math.multiplyExact(D, g1);
            long right1 = Math.addExact(
                    Math.multiplyExact(2L * a, alpha),
                    Math.multiplyExact(Math.addExact(b, g), beta));
            
            Iterator<XYPair> eq1 = new EmptyIterator<>();
            if (right1 % Dg1 == 0) {
                // TODO: do this in LinearSolver instead
                if (Math.addExact(b, g) == 0) {
                    if (alpha % D == 0) {
                        BigInteger alphaByD = BigInteger.valueOf(alpha / D);
                        eq1 = new MappingIterator<>(new IntegerIterator(), y -> { return new XYPair(alphaByD, y); });
                    }
                } else {
                    eq1 = LinearSolver.solve(2L * a / g1, Math.addExact(b, g) / g1, Math.negateExact(right1) / Dg1);
                }
            }
            
            long Dg2 = Math.multiplyExact(D, g2);
            long right2 = Math.addExact(
                    Math.multiplyExact(2L * a, alpha),
                    Math.multiplyExact(Math.subtractExact(b, g), beta));
            
            Iterator<XYPair> eq2 = new EmptyIterator<>();
            if (right2 % Dg2 == 0) {
                if (Math.subtractExact(b, g) == 0) {
                    if (alpha % D == 0) {
                        BigInteger alphaByD = BigInteger.valueOf(alpha / D);
                        eq2 = new MappingIterator<>(new IntegerIterator(), y -> { return new XYPair(alphaByD, y); });
                    }
                } else {
                    eq2 = LinearSolver.solve(2L * a / g2, Math.subtractExact(b, g) / g2, Math.negateExact(right2) / Dg2);
                }
            }
            
            return MergedIterator.merge(eq1, eq2);
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

}
