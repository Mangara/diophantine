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
    public static List<Integer> solve(int a, int b, int c, int n) {
        if (a <= 0) {
            throw new IllegalArgumentException("a too small");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("n too small");
        }
        
        if (n == 1) {
            return Collections.singletonList(0);
        }
        
        if (b == 0) {
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
    public static List<Integer> solveReduced(int a, int c, int n) {
        if (a <= 0 || n <= 1) {
            throw new UnsupportedOperationException("a or n too small");
        }
        
        return bruteForce(a, 0, c, n);
    }
    
    private static List<Integer> bruteForce(int a, int b, int c, int n) {
        List<Integer> result = new ArrayList<>();
        
        BigInteger N = BigInteger.valueOf(n);
        BigInteger A = BigInteger.valueOf(a).remainder(N);
        BigInteger B = BigInteger.valueOf(b).remainder(N);
        BigInteger C = BigInteger.valueOf(c).remainder(N);
        
        for (int x = 0; x < n; x++) {
            BigInteger X = BigInteger.valueOf(x);
            BigInteger calc = A.multiply(X).multiply(X).add(B.multiply(X)).add(C).mod(N);

            if (calc == BigInteger.ZERO) {
                result.add(x);
            }
        }
        
        return result;
    }
}
