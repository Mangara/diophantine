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
import com.github.mangara.diophantine.utils.Divisors;
import java.math.BigInteger;
import java.util.ArrayList;
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
        // Ensure gcd(a, f) = 1
        if (Utils.gcd(a, f) != 1) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        // Ensure a > 0 and f < 0
        if (a <= 0 || f >= 0) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        return solveReduced(a, b, c, -f);
    }

    // Pre: a > 0, f < 0, gcd(a, f) = 1, D = b^2 - 4ac < 0 and not a perfect square
    private static Iterator<XYPair> solveReduced(int a, int b, int c, int n) {
        // If a x^2 + b xy + c y^2 + f = 0 with gcd(x, y) = h, f must be divisible by h^2.
        // So to find all such (x, y), we can solve a X^2 + b XY + c Y^2 + f/h^2 = 0 for relatively prime (X, Y).
        // We then obtain (x, y) = (hX, hY).
        List<XYPair> solutions = new ArrayList<>();
        
        for (Long divisor : Divisors.getSquareDivisors(n)) {
            List<XYPair> primitive = getPrimtiveSolutions(a, b, c, (int) (n / divisor));
            
            BigInteger bigDivisor = BigInteger.valueOf(divisor);
            for (XYPair sol : primitive) {
                solutions.add(new XYPair(sol.x.multiply(bigDivisor), sol.y.multiply(bigDivisor)));
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
            // P = (at^2 + bt + c) / n
            long P = Math.addExact(
                    Math.multiplyExact(a, Math.multiplyExact((long) theta, (long) theta)),
                    Math.addExact(Math.multiplyExact(b, (long) theta), c))
                    / n;
            
            if (D < -4 && P == 1) {
                solutions.add(exceptionalSolution(-1, 0, theta, n));
                solutions.add(exceptionalSolution(1, 0, theta, n));
                continue;
            }
            
            // Q = 2at + b
            long Q = Math.addExact(Math.multiplyExact(2, Math.multiplyExact(a, (long) theta)), b);
            
        }
        
        return solutions;
    }

    private static XYPair exceptionalSolution(long u, long y, int theta, int n) {
        // (tu - ny, u)
        return new XYPair(
                Math.subtractExact(
                        Math.multiplyExact(theta, u), 
                        Math.multiplyExact(n, y)
                ), 
                u
        );
    }
    
    
}
