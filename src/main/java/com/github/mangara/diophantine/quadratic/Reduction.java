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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Transforms a restricted equation a x^2 + b xy + c y^2 + f = 0
 * in which gcd(a, f) > 1 into one with gcd(a, f) = 1.
 */
public class Reduction {

    public static Reduction forEquation(RestrictedEquation eq) {
        // Find a unimodular transform into a form with GCD(a, f) = 1
        // 1. Find relatively prime integers alpha and gamma such that a alpha^2 + b alpha gamma + c gamma^2 = A with gcd(A, f) = 1
        XYPair alphaGamma = findAlphaGamma(eq);
        BigInteger alpha = alphaGamma.x, gamma = alphaGamma.y;
        
        // 2. Find integers beta and delta such that alpha delta - beta gamma = 1
        XYPair betaDelta = findBetaDelta(alpha, gamma);
        BigInteger beta = betaDelta.x, delta = betaDelta.y;
        
        return new Reduction(alpha, beta, gamma, delta);
    }
    
    private static XYPair findAlphaGamma(RestrictedEquation eq) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static XYPair findBetaDelta(BigInteger alpha, BigInteger gamma) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private final BigInteger alpha, beta, gamma, delta;

    public Reduction(BigInteger alpha, BigInteger beta, BigInteger gamma, BigInteger delta) {
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.delta = delta;
    }
    
    public RestrictedEquation reduce(RestrictedEquation eq) {
        // Substitute x = alpha X + beta Y; y = gamma X + delta Y to obtain A X^2 + B XY + C Y^2 + f = 0

        // A = a alpha^2 + b alpha gamma + c gamma^2
        BigInteger A = eq.a.multiply(alpha).multiply(alpha)
                .add(eq.b.multiply(alpha).multiply(gamma))
                .add(eq.c.multiply(gamma).multiply(gamma));

        // B = 2 a alpha beta + b alpha delta + b beta gamma + 2 c gamma delta
        BigInteger B = eq.a.multiply(BigInteger.TWO).multiply(alpha).multiply(beta)
                .add(eq.b.multiply(alpha).multiply(delta))
                .add(eq.b.multiply(beta).multiply(gamma))
                .add(eq.c.multiply(BigInteger.TWO).multiply(gamma).multiply(delta));

        // C = a beta^2 + b beta delta + c delta^2
        BigInteger C = eq.a.multiply(beta).multiply(beta)
                .add(eq.b.multiply(beta).multiply(delta))
                .add(eq.c.multiply(delta).multiply(delta));

        return new RestrictedEquation(A, B, C, eq.f);
    }
    
    public List<XYPair> unreduce(List<XYPair> reducedSolutions) {
        List<XYPair> originalSolutions = new ArrayList<>(reducedSolutions.size());

        for (XYPair sol : reducedSolutions) {
            // x = alpha X + beta Y
            BigInteger x = alpha.multiply(sol.x).add(beta.multiply(sol.y));
            // y = gamma X + delta Y
            BigInteger y = gamma.multiply(sol.x).add(delta.multiply(sol.y));

            originalSolutions.add(new XYPair(x, y));
        }

        return originalSolutions;
    }
}
