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
package io.github.mangara.diophantine.quadratic;

import io.github.mangara.diophantine.Utils;
import io.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
import java.util.Iterator;

public class HyperbolicSolver {

    /**
     * Solves the quadratic Diophantine equation
     * a x^2 + b xy + c y^2 + d x + e y + f = 0,
     * given that D = b^2 - 4ac > 0 and not a perfect square.
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(long a, long b, long c, long d, long e, long f) {
        return solve(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(d), BigInteger.valueOf(e), BigInteger.valueOf(f));
    }

    /**
     * Solves the quadratic Diophantine equation
     * a x^2 + b xy + c y^2 + d x + e y + f = 0,
     * given that D = b^2 - 4ac > 0 and not a perfect square.
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(BigInteger a, BigInteger b, BigInteger c, BigInteger d, BigInteger e, BigInteger f) {
        if (d.signum() == 0 && e.signum() == 0) {
            return RestrictedHyperbolicSolver.solve(a, b, c, f);
        }

        if (a.signum() > 0 && a.gcd(b).gcd(c).gcd(d).gcd(e).equals(BigInteger.ONE)) {
            return solveWithFloridaTransform(a, b, c, d, e, f);
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Pre-condition: a > 0 && (d != 0 || e != 0) && D > 0 and non-square && ae^2 - bde + cd^2 + fD != 0 && gcd(a, b, c, d, e) = 1
    private static Iterator<XYPair> solveWithFloridaTransform(BigInteger a, BigInteger b, BigInteger c, BigInteger d, BigInteger e, BigInteger f) {
        BigInteger D = Utils.discriminant(a, b, c);
        BigInteger alpha = Utils.legendreAlpha(b, c, d, e);
        BigInteger beta = Utils.legendreBeta(a, b, d, e);

        BigInteger gcdA = alpha.gcd(D);
        BigInteger gcdB = beta.gcd(D);

        BigInteger r1 = alpha.divide(gcdA);
        BigInteger r2 = D.divide(gcdA);
        BigInteger s1 = beta.divide(gcdB);
        BigInteger s2 = D.divide(gcdB);

        BigInteger A = a.multiply(s2).multiply(s2); // a * s2 * s2
        BigInteger B = b.multiply(r2).multiply(s2); // b * r2 * s2;
        BigInteger C = c.multiply(r2).multiply(r2); // c * r2 * r2;

        // Simplify
        BigInteger G = A.gcd(B).gcd(C);
        A = A.divide(G);
        B = B.divide(G);
        C = C.divide(G);

        // M = -1 * r2 * r2 * s2 * s2 * (a * e * e - b * d * e + c * d * d + f * D) / (D * G)
        BigInteger M = r2.multiply(r2).multiply(s2).multiply(s2).multiply(
                a.multiply(e).multiply(e)
                        .subtract(b.multiply(d).multiply(e))
                        .add(c.multiply(d).multiply(d))
                        .add(f.multiply(D))
        ).divide(D).divide(G)
                .negate();
        
        BigInteger D2 = Utils.discriminant(A, B, C);

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
