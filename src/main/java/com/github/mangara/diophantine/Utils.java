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
}
