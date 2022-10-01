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

import com.github.mangara.diophantine.XYPair;
import com.github.mangara.diophantine.utils.Divisors;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * This implementation is based on Keith Matthews' paper
 * "The Diophantine equation a x^2 + b xy + c y^2 = N, D = b^2 - 4ac > 0"
 * Journal de Théorie des Nombres de Bordeaux 14 (2002), 257-270
 */
public class RestrictedHyperbolicSolver {

    /**
     * Solves the quadratic Diophantine equation 
     * a x^2 + b xy + c y^2 + f = 0,
     * given that D = b^2 - 4ac > 0 and not a perfect square.
     *
     * @param a
     * @param b
     * @param c
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(long a, long b, long c, long f) {
        return solve(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f));
    }
    
    /**
     * Solves the quadratic Diophantine equation 
     * a x^2 + b xy + c y^2 + f = 0,
     * given that D = b^2 - 4ac > 0 and not a perfect square.
     *
     * @param a
     * @param b
     * @param c
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(BigInteger a, BigInteger b, BigInteger c, BigInteger f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Solves the quadratic Diophantine equation
     * a x^2 + b xy + c y^2 + f = 0,
     * given that D = b^2 - 4ac > 0 and not a perfect square.
     *
     * @param a
     * @param b
     * @param c
     * @param f
     * @return A solution (x, y) from each family. If gcd(a, f) = 1, this is the
     * solution with least positive y.
     */
    public static List<XYPair> getRepresentativeSolutions(BigInteger a, BigInteger b, BigInteger c, BigInteger f) {
        RestrictedEquation eq = new RestrictedEquation(a, b, c, f).withoutCommonDivisor();
        
        if (eq == RestrictedEquation.NO_SOLUTIONS) {
            return Collections.emptyList();
        }
        
        if (eq.a.gcd(eq.f).equals(BigInteger.ONE)) {
            return getRepresentativeSolutionsReduced(eq);
        }
        
        Reduction reduction = Reduction.forEquation(eq);
        List<XYPair> reducedSolutions = getRepresentativeSolutionsReduced(reduction.reduce(eq));
        return reduction.unreduce(reducedSolutions);
    }

    // Pre: D = b^2 - 4ac > 0, D not a perfect square, gcd(a, b, c) = 1, gcd(a, f) = 1
    private static List<XYPair> getRepresentativeSolutionsReduced(RestrictedEquation eq) {
        // If x and y share a factor d, then -f = a x^2 + b xy + c y^2 is divisible by d^2
        // So we find all square factors of f (including 1) and solve a(x/d)^2 + b(x/d)(y/d) + c(y/d)^2 = -(f/d^2) in relatively prime x/d and y/d
        List<XYPair> solutions = new ArrayList<>();
        List<Long> squareDivisors = Divisors.getSquareDivisors(eq.absF.longValueExact());
        
        for (Long div : squareDivisors) {
            BigInteger divisor = BigInteger.valueOf(div);
            BigInteger factor = divisor.sqrt();
            
            List<XYPair> primitiveSolutions = getPrimitiveSolutions(eq.a, eq.b, eq.c, eq.f.divide(divisor), eq.D);
            
            for (XYPair sol : primitiveSolutions) {
                solutions.add(new XYPair(sol.x.multiply(factor), sol.y.multiply(factor)));
            }
        }
        
        return solutions;
    }

    // Pre: D = b^2 - 4ac > 0, D not a perfect square, gcd(a, b, c) = 1, gcd(a, f) = 1
    private static List<XYPair> getPrimitiveSolutions(BigInteger a, BigInteger b, BigInteger c, BigInteger f, BigInteger D) {
        int signN = f.negate().signum();
        BigInteger absF = f.abs();
        List<BigInteger> thetas = UnaryCongruenceSolver.solve(a, b, c, absF);
        Set<XYPair> primitiveSolutions = new HashSet<>();
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
