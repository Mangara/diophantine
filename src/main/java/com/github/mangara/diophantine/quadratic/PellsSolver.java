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
import java.util.Iterator;

/**
 * Solves variations on the Pell's equation: x^2 - D y ^2 = 1.
 * 
 * Based on "Solving the generalized Pell equation x^2 - Dy^2 = N" by John P. Robertson.
 * https://web.archive.org/web/20180831180333/http://www.jpr2718.org/pell.pdf
 */
public class PellsSolver {

    /**
     * Returns an iterator over all solutions to the Diophantine
     * equation x^2 - D y^2 = 4. D must not be a perfect square.
     *
     * @param D
     * @return
     */
    public static Iterator<XYPair> solvePellsFour(BigInteger D) {
        XYPair minimalPositiveSolution = leastPositivePellsFourSolution(D);
        return new PellsFourIterator(D, minimalPositiveSolution);
    }

    private static XYPair leastPositivePellsFourSolution(BigInteger D) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class PellsFourIterator implements Iterator<XYPair> {

        public PellsFourIterator(BigInteger D, XYPair minimalPositiveSolution) {
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public XYPair next() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
