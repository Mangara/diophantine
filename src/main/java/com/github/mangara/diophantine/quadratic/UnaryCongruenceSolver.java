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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnaryCongruenceSolver {
    
    /**
     * Solves the quadratic congruence
     *  a x^2 + b x + c = 0 (mod n)
     * given that a > 0 and n > 1.
     * 
     * @param a
     * @param b
     * @param c
     * @param n
     * @return all integer solutions 0 <= x < n to a x^2 + b x + c = 0 (mod n)
     */
    public static List<BigInteger> solve(long a, long b, long c, long n) {
        return solve(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(n));
    }
    
    /**
     * Solves the quadratic congruence
     *  a x^2 + b x + c = 0 (mod n)
     * given that a > 0 and n > 1.
     * 
     * @param a
     * @param b
     * @param c
     * @param n
     * @return all integer solutions 0 <= x < n to a x^2 + b x + c = 0 (mod n)
     */
    public static List<BigInteger> solve(BigInteger a, BigInteger b, BigInteger c, BigInteger n) {
        if (a.signum() <= 0) {
            throw new IllegalArgumentException("a too small");
        }
        if (n.signum() <= 0) {
            throw new IllegalArgumentException("n too small");
        }
        
        if (n.equals(BigInteger.ONE)) {
            return Collections.singletonList(BigInteger.ZERO);
        }
        
        if (b.signum() == 0) {
            return solveReduced(a, c, n);
        } else {
            return bruteForce(a, b, c, n);
        }
    }
    
    /**
     * Solves the quadratic congruence
     *  a x^2 + c = 0 (mod n)
     * given that a > 0 and n > 1.
     *  
     * @param a
     * @param c
     * @param n
     * @return all integer solutions 0 <= x < n to a x^2 + c = 0 (mod n)
     */
    public static List<BigInteger> solveReduced(long a, long c, long n) {
        return solveReduced(BigInteger.valueOf(a), BigInteger.valueOf(c), BigInteger.valueOf(n));
    }
    
    /**
     * Solves the quadratic congruence
     *  a x^2 + c = 0 (mod n)
     * given that a > 0 and n > 1.
     *  
     * @param a
     * @param c
     * @param n
     * @return all integer solutions 0 <= x < n to a x^2 + c = 0 (mod n)
     */
    public static List<BigInteger> solveReduced(BigInteger a, BigInteger c, BigInteger n) {
        if (a.signum() <= 0) {
            throw new IllegalArgumentException("a too small");
        }
        if (n.signum() <= 0) {
            throw new IllegalArgumentException("n too small");
        }
        
        if (n.equals(BigInteger.ONE)) {
            return Collections.singletonList(BigInteger.ZERO);
        }
        
        return bruteForce(a, BigInteger.ZERO, c, n);
    }
    
    private static List<BigInteger> bruteForce(BigInteger a, BigInteger b, BigInteger c, BigInteger n) {
        List<BigInteger> result = new ArrayList<>();
        
        for (BigInteger x = BigInteger.ZERO; x.compareTo(n) < 0; x = x.add(BigInteger.ONE)) {
            BigInteger calc = a.multiply(x).multiply(x).add(b.multiply(x)).add(c).mod(n);

            if (calc.signum() == 0) {
                result.add(x);
            }
        }
        
        return result;
    }
}
