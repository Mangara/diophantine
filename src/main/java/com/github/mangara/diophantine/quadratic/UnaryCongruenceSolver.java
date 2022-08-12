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

import java.util.List;

public class UnaryCongruenceSolver {
    
    /**
     * Solves the quadratic congruence
     *  a x^2 + b x + c = 0 (mod N)
     * given that a > 0 and N > 1.
     * 
     * @param a
     * @param b
     * @param c
     * @param N
     * @return all integer solutions to a x^2 + b x + c = 0 (mod N)
     */
    public static List<Integer> solve(int a, int b, int c, int N) {
        if (a <= 0 || N <= 1) {
            throw new UnsupportedOperationException("a or N too small");
        }
        
        if (b == 0) {
            return solveReduced(a, c, N);
        } else if (b % 2 == 0) {
            return solveEven(a, b, c, N);
        } else {
            return solveOdd(a, b, c, N);
        }
    }
    
    /**
     * Solves the quadratic congruence
     *  a x^2 + c = 0 (mod N)
     * given that a > 0 and N > 1.
     *  
     * @param a
     * @param c
     * @param N
     * @return 
     */
    public static List<Integer> solveReduced(int a, int c, int N) {
        if (a <= 0 || N <= 1) {
            throw new UnsupportedOperationException("a or N too small");
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Pre: b != 0 and b is even
    private static List<Integer> solveEven(int a, int b, int c, int N) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Pre: b is odd
    private static List<Integer> solveOdd(int a, int b, int c, int N) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
