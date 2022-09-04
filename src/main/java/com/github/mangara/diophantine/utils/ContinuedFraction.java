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

import com.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContinuedFraction {

    public static final int NO_REPETITION = -1;

    private final List<Long> coefficients;
    private final int repetitionStart;

    /**
     * Returns the continued fraction of a/c.
     *
     * @param a
     * @param c
     * @return
     */
    public static ContinuedFraction ofFraction(long a, long c) {
        return ofExpression(a, 0, c);
    }
    
    /**
     * Returns the continued fraction of sqrt(b).
     *
     * @param b
     * @return
     */
    public static ContinuedFraction ofRoot(long b) {
        return ofExpression(0, b, 1);
    }
    
    /**
     * Returns the continued fraction of (a + sqrt(b))/c.
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static ContinuedFraction ofExpression(long a, long b, long c) {
        BigInteger A = BigInteger.valueOf(a), B = BigInteger.valueOf(b), C = BigInteger.valueOf(c);
        if (B.subtract(A.multiply(A)).remainder(C) != BigInteger.ZERO) { // b - a^2 is not divisible by c, multiply by c first so that all divisions are exact
            A = A.multiply(C);
            B = B.multiply(C).multiply(C);
            C = C.multiply(C);
        }

        List<Long> coefficients = new ArrayList<>();
        Map<XYPair, Integer> pairs = new HashMap<>();
        
        BigInteger[] sqrtBAndRemainder = B.sqrtAndRemainder();
        BigInteger sqrtB = sqrtBAndRemainder[0];
        boolean bIsPerfectSquare = sqrtBAndRemainder[1].signum() == 0;

        long coefficient = coefficient(A, sqrtB, C, bIsPerfectSquare);
        XYPair pair = new XYPair(A, C);
        int i = 0;

        do {
            coefficients.add(coefficient);
            pairs.put(pair, i);
            i++;

            A = BigInteger.valueOf(coefficient).multiply(C).subtract(A); // coefficient * C - A;
            C = B.subtract(A.multiply(A)).divide(C); // (B - A * A) / C;

            if (C.signum() == 0) {
                return new ContinuedFraction(coefficients, NO_REPETITION);
            }

            coefficient = coefficient(A, sqrtB, C, bIsPerfectSquare);
            
            pair = new XYPair(A, C);
        } while (!pairs.containsKey(pair));

        return new ContinuedFraction(coefficients, pairs.get(pair));
    }
    
    // floor((a + sqrt(b))/c)
    private static long coefficient(BigInteger A, BigInteger sqrtB, BigInteger C, boolean bIsPerfectSquare) {
        BigInteger top = A.add(sqrtB);
        long coefficient = top.divide(C).longValueExact();
        
        if (top.remainder(C) == BigInteger.ZERO && C.signum() == -1 && !bIsPerfectSquare) {
            coefficient--;
        } else if (top.signum() * C.signum() == -1) {
            coefficient--;
        }
        
        return coefficient;
    }
    
    /**
     * Creates a new continued fraction.
     *
     * Given coefficients [a0, a1, ..., an] and repetitionStart k,
     * the continued fraction is either
     *
     * [a0; a1, ..., an] if k == NO_REPETITION, or
     * [a0; a1, ..., (ak, ..., an)] if 0 <= k < n
     *
     * @param coefficients
     * @param repetitionStart
     */
    public ContinuedFraction(List<Long> coefficients, int repetitionStart) {
        if (coefficients.isEmpty()) {
            throw new IllegalArgumentException("coefficients may not be empty");
        }
        if (repetitionStart >= coefficients.size()) {
            throw new IllegalArgumentException("repetitionStart must be a 0-based index of a coefficient, received " + repetitionStart + " with only " + coefficients.size() + " coefficients.");
        }

        this.coefficients = coefficients;
        this.repetitionStart = repetitionStart;
    }

    /**
     * Creates a new finite continued fraction with the given coefficients.
     *
     * @param coefficients
     */
    public ContinuedFraction(List<Long> coefficients) {
        this(coefficients, NO_REPETITION);
    }

    /**
     * Returns a list of all coefficients.
     *
     * @return
     */
    public List<Long> getCoefficients() {
        return coefficients;
    }

    /**
     * Returns the 0-based index of the first coefficient that repeats if this
     * continued fraction is infinite, or NO_REPETITION otherwise.
     *
     * @return
     */
    public int getRepetitionStart() {
        return repetitionStart;
    }

    /**
     * Returns the list of coefficients that repeat if this continued fraction
     * is infinite, or an empty list otherwise.
     *
     * @return
     */
    public List<Long> getRepeatingCoefficients() {
        if (repetitionStart == NO_REPETITION) {
            return Collections.emptyList();
        } else {
            return coefficients.subList(repetitionStart, coefficients.size());
        }
    }

    /**
     * Returns the number of repeating coefficients if this continued fraction
     * is infinite, or 0 otherwise.
     *
     * @return
     */
    public int getPeriod() {
        if (repetitionStart == NO_REPETITION) {
            return 0;
        } else {
            return coefficients.size() - repetitionStart;
        }
    }

    /**
     * Returns the first length coefficients of this continued fraction.
     *
     * If length is greater than the number of coefficients, the repeating
     * coefficients are repeated as often as necessary if this continued
     * fraction is infinite. Otherwise, all coefficients are returned.
     *
     * @param length
     * @return
     */
    public List<Long> getCoefficients(int length) {
        List<Long> repeating = getRepeatingCoefficients();
        List<Long> result = new ArrayList<>(coefficients);

        if (!repeating.isEmpty()) {
            while (result.size() < length) {
                result.addAll(repeating);
            }
        }

        return result.subList(0, Math.min(length, result.size()));
    }

    /**
     * Returns the first length convergents of this continued fraction.
     *
     * Algorithm adapted from Moore (1964) An Introduction to Continuous
     * Fractions.
     *
     * @param length
     * @return a list of convergents x/y
     */
    public List<XYPair> getConvergents(int length) {
        if (length <= 0) {
            return Collections.emptyList();
        }

        List<Long> cfs = getCoefficients(length);
        List<BigInteger> num = new ArrayList<>(length + 1);
        List<BigInteger> den = new ArrayList<>(length + 1);
        List<XYPair> result = new ArrayList<>(length);

        num.add(BigInteger.ONE); // r_0 = 1
        num.add(BigInteger.valueOf(cfs.get(0))); // r_1 = a_1

        den.add(BigInteger.ZERO); // s_0 = 0
        den.add(BigInteger.ONE); // s_1 = 1

        result.add(new XYPair(num.get(1), den.get(1))); // C_1 = r_1 / s_1 = a_1 / 1

        for (int i = 1; i < length; i++) {
            BigInteger coeff = BigInteger.valueOf(cfs.get(i));

            // r_n = a_n r_(n-1) + r_(n-2)
            BigInteger newNum = coeff.multiply(num.get(i)).add(num.get(i - 1));
            num.add(newNum);

            // s_n = a_n s_(n-1) + s_(n-2)
            BigInteger newDen = coeff.multiply(den.get(i)).add(den.get(i - 1));
            den.add(newDen);

            // C_n = r_n / s_n
            result.add(new XYPair(newNum, newDen));
        }

        return result;
    }
}
