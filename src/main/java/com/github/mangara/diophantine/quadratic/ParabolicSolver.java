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

import com.github.mangara.diophantine.EmptyIterator;
import com.github.mangara.diophantine.LinearSolver;
import com.github.mangara.diophantine.Utils;
import com.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
import java.util.Iterator;

public class ParabolicSolver {

    /**
     * Solves the quadratic Diophantine equation 
     * a x^2 + b xy + c y^2 + d x + e y + f = 0,
     * given that D = b^2 - 4ac = 0 and not all of a, b, and c are zero.
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @return an iterator over all integer solutions (x, y)
     */
    public static Iterator<XYPair> solve(int a, int b, int c, int d, int e, int f) {
        if (a == 0) {
            // TODO: Ensure a is not 0 by swapping a and c
            throw new UnsupportedOperationException("Not implemented yet.");
        }
        
        BigInteger u = computeU(a, b, d, e);
        
        if (u.signum() == 0) {
            return solveSimple(a, b, c, d, e, f);
        } else {
            return solveGeneral(a, b, c, d, e, f);
        }
    }

    // Pre: D = 0, a != 0, u = 2(bd - 2ae) = 0
    private static Iterator<XYPair> solveSimple(int a, int b, int c, int d, int e, int f) {
        BigInteger v = computeV(a, d, f);
        
        if (v.signum() == 0) {
            if (a > Integer.MAX_VALUE / 2) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            
            return LinearSolver.solve(2 * a, b, d);
        }
        
        if (v.signum() < 0) {
            return new EmptyIterator<>();
        }
        
        BigInteger[] sqrtV = v.sqrtAndRemainder();
        if (sqrtV[1].signum() != 0) {
            return new EmptyIterator<>();
        }
        
        BigInteger g = sqrtV[0];
        long h = Utils.gcd(2L * a, b);
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static Iterator<XYPair> solveGeneral(int a, int b, int c, int d, int e, int f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private static BigInteger computeU(int a, int b, int d, int e) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger B = BigInteger.valueOf(b);
        BigInteger D = BigInteger.valueOf(d);
        BigInteger E = BigInteger.valueOf(e);
        
        // u = 2(bd - 2ae)
        return BigInteger.TWO.multiply(B.multiply(D).subtract(BigInteger.TWO.multiply(A).multiply(E)));
    }
    
    private static BigInteger computeV(int a, int d, int f) {
        BigInteger A = BigInteger.valueOf(a);
        BigInteger D = BigInteger.valueOf(d);
        BigInteger F = BigInteger.valueOf(f);
        
        // v = d^2 - 4af
        return D.multiply(D).subtract(BigInteger.valueOf(4).multiply(A).multiply(F));
    }
}
