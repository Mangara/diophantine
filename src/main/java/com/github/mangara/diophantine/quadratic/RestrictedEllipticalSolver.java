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

import com.github.mangara.diophantine.Utils;
import com.github.mangara.diophantine.XYPair;
import com.github.mangara.diophantine.iterators.EmptyIterator;
import com.github.mangara.diophantine.utils.ContinuedFraction;
import com.github.mangara.diophantine.utils.Divisors;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Solves some elliptical quadratic Diophantine equations in two variables.
 * <p>
 * The method is based on K. R. Matthews, "Lagrange's algorithm revisited: solving \(at^2 + btu + cu^2 = n\) in the case of negative discriminant", Journal of Integer Sequences, Vol. 17 (2014), Article 14.11.1, https://cs.uwaterloo.ca/journals/JIS/VOL17/Matthews/matt10.html
 */
public class RestrictedEllipticalSolver {

    /**
     * Solves the quadratic Diophantine equation
     * a x^2 + b xy + c y^2 + f = 0,
     * given that D = b^2 - 4ac < 0 and not a perfect square.
     *
     * @param a
     * @param b
     * @param c
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(int a, int b, int c, int f) {
        if (f == 0) {
            // We can't get here from QuadraticSolver, as it ends up
            // in the trivial case, but I included it for completeness.
            
            // Solving for x with the quadratic formula gives
            //  D' = Dy^2 - 4af = Dy^2
            // If y != 0, Dy^2 < 0, so there are no solutions.
            // y = 0 gives ax^2 = 0 => x = 0
            return Collections.singletonList(new XYPair(0, 0)).iterator();
        }
        
        // We know: b^2 - 4ac < 0, so ac > 0, which means 
        // a != 0, c != 0 and they have the same sign
        
        if (a < 0) {
            // Multiply by -1
            a = -a;
            b = -b;
            c = -c;
            f = -f;
        }
        // Now a > 0
        
        if (f > 0) {
            // Solving for x with the quadratic formula gives
            //  D' = Dy^2 - 4af
            // As D < 0 and y^2 >= 0, Dy^2 <= 0.
            // Thus there are no solutions if 4af > 0.
            return new EmptyIterator<>();
        }
        
        return solveSignCorrected(a, b, c, f);
    }
    
    // Pre: a > 0, f < 0, D = b^2 - 4ac < 0 and not a perfect square
    private static Iterator<XYPair> solveSignCorrected(int a, int b, int c, int f) {
        // TODO: Ensure gcd(a, n) = 1
        if (Utils.gcd(a, f) != 1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        return solveReduced(a, b, c, f);
    }

    // Pre: a > 0, n > 0, gcd(a, n) = 1, D = b^2 - 4ac < 0 and not a perfect square
    private static Iterator<XYPair> solveReduced(int a, int b, int c, int f) {
        int n = -f;
        // If a x^2 + b xy + c y^2 = n with gcd(x, y) = h, n must be divisible by h^2.
        // So to find all such (x, y), we can solve a X^2 + b XY + c Y^2 + n/h^2 = 0 for relatively prime (X, Y).
        // We then obtain (x, y) = (hX, hY).
        List<XYPair> solutions = new ArrayList<>();
        
        for (Long divisor : Divisors.getSquareDivisors(n)) {
            List<XYPair> primitive = getPrimtiveSolutions(a, b, c, (int) (n / divisor));
            
            BigInteger factor = BigInteger.valueOf(divisor).sqrt();
            for (XYPair sol : primitive) {
                solutions.add(new XYPair(sol.x.multiply(factor), sol.y.multiply(factor)));
            }
        }
        
        return solutions.iterator();
    }

    private static List<XYPair> getPrimtiveSolutions(int a, int b, int c, int n) {
        List<XYPair> solutions = new ArrayList<>();
        
        // Solve at^2 + bt + c = 0 (mod n) for -n/2 < t <= n/2
        List<Integer> congruenceSolutions = UnaryCongruenceSolver.solve(a, b, c, n);
        List<Integer> thetas = congruenceSolutions.stream()
                .map(t -> t > n / 2 ? t - n : t)
                .collect(Collectors.toList());
        
        long D = Utils.discriminant(a, b, c);
        
        for (Integer theta : thetas) {
            // By substituting x = tu - ny, we obtain
            //  Pu^2 + Quy + Ry^2 = 1, with
            
            // P = (at^2 + bt + c) / n
            long P = Math.addExact(
                    Math.multiplyExact(a, Math.multiplyExact((long) theta, (long) theta)),
                    Math.addExact(Math.multiplyExact(b, (long) theta), c))
                    / n;
            // Q = -(2at + b)
            long Q = Math.negateExact(Math.addExact(Math.multiplyExact(2, Math.multiplyExact(a, (long) theta)), b));
            // R = na
            
            if (D < -4 && P == 1) {
                solutions.addAll(solutions(1, 0, theta, n));
            } else if (D == -4 && P == 1) {
                long N = Q / 2;
                solutions.addAll(solutions(1, 0, theta, n));
                solutions.addAll(solutions(N, -1, theta, n));
            } else if (D == -4 && P == 2) {
                long N = Q / 2;
                solutions.addAll(solutions((N - 1) / 2, -1, theta, n));
                solutions.addAll(solutions((N + 1) / 2, -1, theta, n));
            } else if (D == -3 && P == 1) {
                long N = (Q - 1) / 2;
                solutions.addAll(solutions(1, 0, theta, n));
                solutions.addAll(solutions(N, -1, theta, n));
                solutions.addAll(solutions(N + 1, -1, theta, n));
            } else if (D == -3 && P == 3) {
                long N = (Q - 1) / 2;
                solutions.addAll(solutions((N - 1)/3, -1, theta, n));
                solutions.addAll(solutions((2 * N + 1)/3, -2, theta, n));
                solutions.addAll(solutions((N + 2)/3, -1, theta, n));
            } else {
                long bound = (long) Math.sqrt(Math.multiplyExact(4, P) / -D);
                List<XYPair> convergents = ContinuedFraction.ofFraction(-Q, 2 * P).getConvergents();
                
                for (int i = 0; i < convergents.size(); i++) {
                    XYPair convergent = convergents.get(i);
                    
                    try {
                        if (convergent.y.longValueExact() > bound) {
                            break;
                        }
                    } catch (ArithmeticException ex) {
                        // convergent.y is too large for a long, so certainly larger than bound
                        break;
                    }
                    
                    BigInteger Ai = convergent.x;
                    BigInteger Bi = convergent.y;
                    
                    // Test if (Ai, Bi) is a solution to the reduced equation
                    BigInteger result = Ai.multiply(Ai).multiply(BigInteger.valueOf(P))
                            .add(Ai.multiply(Bi).multiply(BigInteger.valueOf(Q)))
                            .add(Bi.multiply(Bi).multiply(BigInteger.valueOf(n * a)));
                    
                    if (result.equals(BigInteger.ONE)) {
                        solutions.addAll(solutions(Ai.longValueExact(), Bi.longValueExact(), theta, n));
                        
                        if (D == -3 || D == -4) {
                            XYPair convergent2 = convergents.get(i + 1);
                            solutions.addAll(solutions(convergent2.x.longValueExact(), convergent2.y.longValueExact(), theta, n));
                        }
                        
                        if (D == -3) {
                            XYPair convergent3 = convergents.get(i + 2);
                            solutions.addAll(solutions(convergent3.x.longValueExact(), convergent3.y.longValueExact(), theta, n));
                        }
                        
                        break;
                    }
                }
            }
        }
        
        return solutions;
    }

    private static List<XYPair> solutions(long u, long y, int theta, int n) {
        long x = Math.subtractExact(
                Math.multiplyExact(theta, u), 
                Math.multiplyExact(n, y)
        );
        
        // (tu - ny, u)
        return Arrays.asList(new XYPair(x, u), new XYPair(Math.negateExact(x), Math.negateExact(u)));
    }
}
