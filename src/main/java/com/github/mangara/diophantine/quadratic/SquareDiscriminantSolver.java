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
import com.github.mangara.diophantine.iterators.MergedIterator;
import com.github.mangara.diophantine.utils.Divisors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        // Legendre's transformation with Dx = X + alpha and Dy = Y + beta gives
        //  aX^2 + bXY + cY^2 = k
        // With a = 0, this reduces to
        //  Y(bX + cY) = k
        
        long D = Utils.discriminant(a, b, c);
        long alpha = Utils.legendreAlpha(b, c, d, e);
        long beta = Utils.legendreBeta(a, b, d, e);
        long k = Utils.legendreConstant(a, b, c, d, e, f, D);
        long h = Utils.gcd(b, c);
        
        if (k == 0) {
            // Y(bX + cY) = 0, so 
            // Y = 0  or  bX + cY = 0
            
            // Dy - beta = 0  =>  Dy = beta
            Iterator<XYPair> yZero = LinearSolver.solve(0, D, Math.negateExact(beta));
            
            // b(Dx - alpha) + c(Dy - beta) = 0  =>  bDx + cDy = b alpha + c beta
            long t = Math.addExact(Math.multiplyExact(b, alpha), Math.multiplyExact(c, beta));
            Iterator<XYPair> yNonZero = LinearSolver.solve(Math.multiplyExact(b, D), Math.multiplyExact(c, D), Math.negateExact(t));
            
            return MergedIterator.merge(yZero, yNonZero);
        } else {
            //  Y((b/h)X + (c/h)Y) = k/h
            // We need to examine all divisors d_i of k/h and solve the system
            //  Y = d_i  and  (b/h)X + (c/h)Y = k/(h d_i)
            
            long b2 = b / h;
            long c2 = c / h;
            long k2 = k / h;
            
            List<XYPair> solutions = new ArrayList<>();
            List<Long> divisors = Divisors.getPositiveAndNegativeDivisors(k2);
            
            for (Long divisor : divisors) {
                long Y = divisor;
                long Xtop = Math.subtractExact(k2 / divisor, Math.multiplyExact(c2, Y));
                
                if (Xtop % b2 == 0) {
                    long X = Xtop / b2;
                    
                    long xD = Math.addExact(X, alpha);
                    long yD = Math.addExact(Y, beta);

                    if (xD % D == 0 && yD % D == 0) {
                        solutions.add(new XYPair(xD / D, yD / D));
                    }
                }
            }
            
            return solutions.iterator();
        }
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

        long alpha = Utils.legendreAlpha(b, c, d, e);
        long beta = Utils.legendreBeta(a, b, d, e);

        // This reduces to
        //  ((2a / g1)X + ((b + g) / g1)Y)((2a / g2)X + ((b - g) / g2)Y) = 4ak / g1g2
        if (k == 0) {
            // If k = 0 we can solve both parts separately
            //  2aX + (b + g)Y = 0  or  2aX + (b - g)Y = 0
            // Subsituting X = Dx - alpha and Y = Dy - beta gives
            //  2aDx + (b + g)Dy = 2a alpha + (b + g) beta  or
            //  2aDx + (b - g)Dy = 2a alpha + (b - g) beta

            long Dg1 = Math.multiplyExact(D, g1);
            long right1 = Math.addExact(
                    Math.multiplyExact(2L * a, alpha),
                    Math.multiplyExact(Math.addExact(b, g), beta));

            Iterator<XYPair> eq1 = new EmptyIterator<>();
            if (right1 % Dg1 == 0) {
                eq1 = LinearSolver.solve(2L * a / g1, Math.addExact(b, g) / g1, Math.negateExact(right1) / Dg1);
            }

            long Dg2 = Math.multiplyExact(D, g2);
            long right2 = Math.addExact(
                    Math.multiplyExact(2L * a, alpha),
                    Math.multiplyExact(Math.subtractExact(b, g), beta));

            Iterator<XYPair> eq2 = new EmptyIterator<>();
            if (right2 % Dg2 == 0) {
                eq2 = LinearSolver.solve(2L * a / g2, Math.subtractExact(b, g) / g2, Math.negateExact(right2) / Dg2);
            }

            return MergedIterator.merge(eq1, eq2);
        } else {
            // We have T1 T2 = 4ak / g1g2, with both T1 and T2 integer
            // so we can test each divisor d_i (positive and negative) of 4ak / g1g2
            // to see if we can solve the system T1 = d_i, T2 = 4ak / g1g2d_i

            List<XYPair> solutions = new ArrayList<>();
            List<Long> divisors = Divisors.getPositiveAndNegativeDivisors(fourAK / g1g2);

            for (Long divisor : divisors) {
                // We need to solve
                //  (1) (2a / g1)X + ((b + g) / g1)Y = divisor
                //  (2) (2a / g2)X + ((b - g) / g2)Y = 4ak / (g1g2 divisor)
                // Adding (g2 / g1) times (2) to (1) gives
                //  (2g / g1) Y = divisor - 4ak / (g1^2 divisor)
                //            Y = ((g1 divisor)^2 - 4ak) / (2g g1 divisor)

                long Ytop = Math.subtractExact(Math.multiplyExact(Math.multiplyExact(g1, g1), Math.multiplyExact(divisor, divisor)), fourAK);
                long Ybottom = Math.multiplyExact(Math.multiplyExact(2, g), Math.multiplyExact(g1, divisor));

                if (Ytop % Ybottom == 0) {
                    long Y = Ytop / Ybottom;

                    // Subsituting into (2a / g1)X + ((b + g) / g1)Y = divisor gives
                    //  X = (g1 divisor - (b + g) Y) / 2a
                    long Xtop = Math.subtractExact(Math.multiplyExact(g1, divisor), Math.multiplyExact(Math.addExact(b, g), Y));
                    long Xbottom = Math.multiplyExact(2, a);

                    if (Xtop % Xbottom == 0) {
                        long X = Xtop / Xbottom;

                        long xD = Math.addExact(X, alpha);
                        long yD = Math.addExact(Y, beta);

                        if (xD % D == 0 && yD % D == 0) {
                            solutions.add(new XYPair(xD / D, yD / D));
                        }
                    }
                }
            }

            return solutions.iterator();
        }
    }

}
