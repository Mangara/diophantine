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
package com.github.mangara.diophantine.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Divisors {

    /**
     * Returns the list of all positive divisors of n.
     *
     * @param n the number to divide, must be positive
     * @return
     */
    public static List<Long> getDivisors(long n) {
        List<Long> factors = Primes.getPrimeFactors(n);
        List<Long> divisors = new ArrayList<>();
        divisors.add(1L);
        long prevFactor = -1;
        int factorCount = -1;

        for (Long f : factors) {
            if (f == prevFactor) {
                factorCount++;
                continue;
            }

            if (prevFactor > 0) {
                divisors.addAll(getNewDivisors(divisors, prevFactor, factorCount));
            }

            prevFactor = f;
            factorCount = 1;
        }

        divisors.addAll(getNewDivisors(divisors, prevFactor, factorCount));

        return divisors;
    }

    private static List<Long> getNewDivisors(List<Long> divisors, long prevFactor, int factorCount) {
        List<Long> newDivisors = new ArrayList<>();

        for (Long d : divisors) {
            long factor = prevFactor;
            for (int i = 0; i < factorCount; i++) {
                newDivisors.add(d * factor);
                factor *= prevFactor;
            }
        }

        return newDivisors;
    }

    /**
     * Returns a list of all divisors of n, with each occurring twice: positive
     * and negative.
     *
     * @param n the number to divide, must be non-zero
     * @return
     */
    public static List<Long> getPositiveAndNegativeDivisors(long n) {
        return getDivisors(Math.abs(n)).stream()
                .flatMap(d -> Stream.of(d, -d))
                .collect(Collectors.toList());
    }
}
