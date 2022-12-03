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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A solver for quadratic Diophantine equations
 * a x^2 + b xy + c y^2 + d x + e y + f = 0,
 * where D = b^2 - 4ac > 0 and not a perfect square.
 */
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
        
        List<XYPair> representativeSolutions = RestrictedHyperbolicSolver.getRepresentativeSolutions(A, B, C, M.negate());
        
        XYPair phiPsi = PellsSolver.leastPositivePellsFourSolution(D);
        BigInteger phi1 = phiPsi.x;
        BigInteger psi1 = phiPsi.y;
        
        // phi2 = (phi1 * phi1 + D * psi1 * psi1) / 2
        BigInteger phi2 = phi1.multiply(phi1)
                .add(D.multiply(psi1).multiply(psi1))
                .divide(BigInteger.TWO);
        BigInteger psi2 = phi1.multiply(psi1); // phi1 * psi1
        
        List<XYPair> solutions = findTransformedSolutions(representativeSolutions, D, A, B, C, r1, r2, s1, s2, phi2, psi2);
        
        return buildIterator(solutions, a, b, c, D, alpha, beta, phi1, psi1, phi2, psi2);
    }
    
    private static BigInteger K1D(BigInteger phi, BigInteger psi, BigInteger alpha, BigInteger beta, BigInteger b, BigInteger c) {
        // K1(v) = alpha - (alpha * (phi1 - b * psi1) / 2 + beta * -c * psi1)
        return alpha.subtract(
                alpha.multiply(phi.subtract(b.multiply(psi))).divide(BigInteger.TWO)
                .add(beta.multiply(c.negate()).multiply(psi))
        );
    }

    private static BigInteger K2D(BigInteger phi, BigInteger psi, BigInteger alpha, BigInteger beta, BigInteger a, BigInteger b) {
        // K2(v) = beta - (alpha * a * psi1 + beta * (phi1 + b * psi1) / 2)
        return beta.subtract(
                alpha.multiply(a).multiply(psi)
                .add(beta.multiply(phi.add(b.multiply(psi))).divide(BigInteger.TWO))
        );
    }

    private static boolean givesSolutions(BigInteger K1D, BigInteger K2D, BigInteger D) {
        return K1D.mod(D).signum() == 0 && K2D.mod(D).signum() == 0;
    }

    private static List<XYPair> findTransformedSolutions(List<XYPair> representativeSolutions, BigInteger D, BigInteger A, BigInteger B, BigInteger C, BigInteger r1, BigInteger r2, BigInteger s1, BigInteger s2, BigInteger phi2, BigInteger psi2) {
        BigInteger D2 = Utils.discriminant(A, B, C);
        
        XYPair uv1 = PellsSolver.leastPositivePellsFourSolution(D2);
        BigInteger minU = uv1.x;
        BigInteger minV = uv1.y;
        
        int k = findK(minU, minV, D, D2, phi2, psi2);
        
        BigInteger u11 = minU.subtract(B.multiply(minV)).divide(BigInteger.TWO);
        BigInteger u12 = minV.multiply(C.negate());
        BigInteger u21 = minV.multiply(A);
        BigInteger u22 = minU.add(B.multiply(minV)).divide(BigInteger.TWO);
        
        List<XYPair> solutions = new ArrayList<>();
        
        // Test T_{mu^j}(X_i, Y_i) and T_{mu^j}(-X_i, -Y_i) for 0 <= j <= k - 1, for each representative solution (X_i, Y_i)
        for (XYPair sol : representativeSolutions) {
            BigInteger x = sol.x, y = sol.y;

            for (int j = 0; j <= k - 1; j++) {
                testSolution(solutions, x, y, r1, r2, s1, s2);
                testSolution(solutions, x.negate(), y.negate(), r1, r2, s1, s2);

                BigInteger nextX = u11.multiply(x).add(u12.multiply(y));
                BigInteger nextY = u21.multiply(x).add(u22.multiply(y));
                x = nextX;
                y = nextY;
            }
        }
        
        return solutions;
    }
    
    private static int findK(BigInteger minU, BigInteger minV, BigInteger D, BigInteger D2, BigInteger phi2, BigInteger psi2) {
        // Find k such that v^2 = mu^k
        BigInteger u = minU;
        BigInteger v = minV;
        
        BigInteger h = D.multiply(BigInteger.valueOf(4)).divide(D2).sqrt(); // This is guaranteed to be an integer
        BigInteger uTarget = phi2;
        BigInteger vTarget = psi2.multiply(h).divide(BigInteger.TWO);
        
        int k = 1;
        
        while (u.compareTo(uTarget) != 0 || v.compareTo(vTarget) != 0) {
            BigInteger nextU = minU.multiply(u).add(minV.multiply(D2).multiply(v)).divide(BigInteger.TWO);
            BigInteger nextV = minU.multiply(v).add(minV.multiply(u)).divide(BigInteger.TWO);
            
            u = nextU;
            v = nextV;
            k++;
        }
        
        return k;
    }
    
    private static void testSolution(List<XYPair> solutions, BigInteger x, BigInteger y, BigInteger r1, BigInteger r2, BigInteger s1, BigInteger s2) {
        if (x.add(r1).mod(r2).signum() == 0 && y.add(s1).mod(s2).signum() == 0) {
            BigInteger transX = x.add(r1).divide(r2);
            BigInteger transY = y.add(s1).divide(s2);

            solutions.add(new XYPair(transX, transY));
        }
    }
    
    private static Iterator<XYPair> buildIterator(List<XYPair> solutions, BigInteger a, BigInteger b, BigInteger c, BigInteger D, BigInteger alpha, BigInteger beta, BigInteger phi1, BigInteger psi1, BigInteger phi2, BigInteger psi2) {
        // Solutions for (phi, psi)?
        BigInteger K1posD = K1D(phi1, psi1, alpha, beta, b, c);
        BigInteger K2posD = K2D(phi1, psi1, alpha, beta, a, b);
        
        if (givesSolutions(K1posD, K2posD, D)) {
            return new FloridaIterator(solutions, phi1, psi1, a, b, c, K1posD.divide(D), K2posD.divide(D));
        }
        
        // Solutions for (-phi, -psi)?
        BigInteger K1negD = K1D(phi1.negate(), psi1.negate(), alpha, beta, b, c);
        BigInteger K2negD = K2D(phi1.negate(), psi1.negate(), alpha, beta, a, b);
        
        if (givesSolutions(K1negD, K2negD, D)) {
            return new FloridaIterator(solutions, phi1.negate(), psi1.negate(), a, b, c, K1negD.divide(D), K2negD.divide(D));
        }
        
        // Solutions for (phi2, -psi2)
        BigInteger K1squareD = K1D(phi2, psi2, alpha, beta, b, c);
        BigInteger K2squareD = K2D(phi2, psi2, alpha, beta, a, b);
            
        return new FloridaIterator(solutions, phi2, psi2, a, b, c, K1squareD.divide(D), K2squareD.divide(D));
    }
    
    private static class FloridaIterator implements Iterator<XYPair> {
        
        private final BigInteger v11;
        private final BigInteger v12;
        private final BigInteger v21;
        private final BigInteger v22;
        private final BigInteger K1;
        private final BigInteger K2;
        private final BigInteger K1neg;
        private final BigInteger K2neg;

        private int solIndex;
        private final int nSolutions;
        private final List<XYPair> positiveSolutions;
        private final List<XYPair> negativeSolutions;

        public FloridaIterator(List<XYPair> solutions, BigInteger phi, BigInteger psi, BigInteger a, BigInteger b, BigInteger c, BigInteger K1, BigInteger K2) {
            v11 = phi.subtract(b.multiply(psi)).divide(BigInteger.TWO); // (phi - b * psi) / 2
            v12 = c.negate().multiply(psi); // -c * psi
            v21 = a.multiply(psi); // a * psi
            v22 = phi.add(b.multiply(psi)).divide(BigInteger.TWO); // (phi + b * psi) / 2
            this.K1 = K1;
            this.K2 = K2;
            K1neg = v22.negate().multiply(K1).add(v12.multiply(K2));
            K2neg = v21.multiply(K1).subtract(v11.multiply(K2));

            this.nSolutions = solutions.size();
            this.positiveSolutions = new ArrayList<>(solutions);
            this.negativeSolutions = new ArrayList<>(solutions);
            solIndex = -nSolutions - 1;
        }

        @Override
        public boolean hasNext() {
            return true;
        }
        
        @Override
        public XYPair next() {
            solIndex++;

            if (solIndex == 0) {
                advanceSolutions();
            } else if (solIndex >= nSolutions) {
                solIndex = -nSolutions;
            }

            return solIndex >= 0 ? positiveSolutions.get(solIndex) : negativeSolutions.get(solIndex + nSolutions);
        }

        private void advanceSolutions() {
            for (ListIterator<XYPair> it = positiveSolutions.listIterator(); it.hasNext();) {
                XYPair sol = it.next();
                BigInteger x = sol.x;
                BigInteger y = sol.y;

                BigInteger nextX = v11.multiply(x).add(v12.multiply(y)).add(K1);
                BigInteger nextY = v21.multiply(x).add(v22.multiply(y)).add(K2);

                it.set(new XYPair(nextX, nextY));
            }

            for (ListIterator<XYPair> it = negativeSolutions.listIterator(); it.hasNext();) {
                XYPair sol = it.next();
                BigInteger x = sol.x;
                BigInteger y = sol.y;

                BigInteger prevX = v22.multiply(x).subtract(v12.multiply(y)).add(K1neg);
                BigInteger prevY = v21.negate().multiply(x).add(v11.multiply(y)).add(K2neg);

                it.set(new XYPair(prevX, prevY));
            }
        }
    }
}
