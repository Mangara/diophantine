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
package com.github.mangara.diophantine;

import java.math.BigInteger;

/**
 * This class contains various utility functions used in multiple parts of the
 * library.
 */
public class Utils {

    /**
     * Computes the GCD (greatest common divisor) of a and b, using the
     * Euclidean algorithm.
     *
     * @param a
     * @param b
     * @return the greatest common divisor of a and b
     */
    public static long gcd(long a, long b) {
        long t;

        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }

        return Math.abs(a);
    }
    
    /**
     * Computes the GCD (greatest common divisor) of a and b, using the
     * Euclidean algorithm.
     *
     * @param a
     * @param b
     * @return the greatest common divisor of a and b
     */
    public static int gcd(int a, int b) {
        return (int) gcd((long) a, (long) b);
    }

    /**
     * Computes the GCD (greatest common divisor) of the given numbers, using
     * repeated invocations of the Euclidean algorithm.
     *
     * @param a
     * @param b
     * @param numbers
     * @return the greatest common divisor of all given numbers
     */
    public static long gcd(long a, long b, long... numbers) {
        long result = gcd(a, b);

        for (long l : numbers) {
            result = gcd(result, l);
        }

        return result;
    }
    
    /**
     * Tests whether the given number is a perfect square.
     * 
     * @param n
     * @return true iff there is an integer x such that n = x^2
     */
    public static boolean isSquare(long n) {
        if (n < 0) {
            return false;
        }
        
        long root = Math.round(Math.sqrt(n));
        return n == root * root;
    }

    /**
     * Computes the discriminant b^2 - 4ac.
     * 
     * @param a
     * @param b
     * @param c
     * @return b^2 - 4ac
     * @throws ArithmeticException if the final value does not fit in a long
     */
    public static long discriminant(long a, long b, long c) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger B = BigInteger.valueOf(b);
        BigInteger C = BigInteger.valueOf(c);
        
        // b * b - 4 * a * c
        return B.multiply(B).subtract(BigInteger.valueOf(4).multiply(A).multiply(C)).longValueExact();
    }
    
    public static long legendreConstant(long a, long b, long c, long d, long e, long f, long D) {
        BigInteger Ba = BigInteger.valueOf(a);
        BigInteger Bb = BigInteger.valueOf(b);
        BigInteger Bc = BigInteger.valueOf(c);
        BigInteger Bd = BigInteger.valueOf(d);
        BigInteger Be = BigInteger.valueOf(e);
        BigInteger Bf = BigInteger.valueOf(f);
        BigInteger BD = BigInteger.valueOf(D);
        
        // -D(ae^2 - bed + cd^2 + fD)
        
//        System.out.printf("a = %d, b = %d, c = %d, d = %d, e = %d, f = %d%n", Ba, );
        
        return BD.negate().multiply(
                Ba.multiply(Be).multiply(Be)
                    .subtract(Bb.multiply(Be).multiply(Bd))
                    .add(Bc.multiply(Bd).multiply(Bd))
                    .add(Bf.multiply(BD))
        ).longValueExact();
    }
}
