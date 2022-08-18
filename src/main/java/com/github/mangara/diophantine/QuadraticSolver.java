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

import com.github.mangara.diophantine.quadratic.*;
import java.util.Iterator;

/**
 * Solves linear Diophantine equations.
 * <p>
 * This class allows you to solve Diophantine equations of the form
 *
 * \[a x^2 + b xy + c y^2 + d x + e y + f = 0\]
 *
 * The methods are based on a note and papers by Keith Matthews:
 * 
 * <ul>
 * <li> Solving the Diophantine equation \(ax^2 + bxy + cy^2 + dx + ey + f = 0\), http://www.numbertheory.org/PDFS/general_quadratic_solution.pdf
 * <li> The Diophantine equation \(ax^2 + bxy + cy^2 = N\), \(D = b^2 - 4ac > 0\), Journal de Théorie des Nombres de Bordeaux, tome 14, no 1 (2002), p. 257-270, http://www.numdam.org/item/?id=JTNB_2002__14_1_257_0
 * <li> Lagrange's Algorithm revisited: solving \(at^2 + btu + cu^2 = n\) in the case of negative discriminant, Journal of Integer Sequences, Vol. 17 (2014), Article 14.11.1, https://cs.uwaterloo.ca/journals/JIS/VOL17/Matthews/matt10.pdf
 * <li> On solving a binary quadratic Diophantine equation (co-authored with John P. Robertson), Rocky Mountain J. Math. 51(4): 1369-1385 (August 2021). DOI: 10.1216/rmj.2021.51.1369
 * </ul>
 */
public class QuadraticSolver {

    /**
     * Solves the quadratic Diophantine equation
     * a x^2 + b xy + c y^2 + d x + e y + f = 0.
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
        if (a == 0 && b == 0 && c == 0) {
            return LinearSolver.solve(d, e, f);
        }
        
        long D = Utils.discriminant(a, b, c);
        
        if (D == 0) {
            return ParabolicSolver.solve(a, b, c, d, e, f);
        } else if (Utils.isSquare(D)) {
            return SquareDiscriminantSolver.solve(a, b, c, d, e, f);
        } else if (Utils.legendreConstant(a, b, c, d, e, f, D) == 0) {
            return solveTrivialCase(a, b, c, d, e, f, D);
        } else if (D < 0) {
            return EllipticalSolver.solve(a, b, c, d, e, f);
        } else {
            return HyperbolicSolver.solve(a, b, c, d, e, f);
        }
    }

    // Pre: D = b^2 - 4ac != 0 && D not a perfect square && Legendre's k = 0
    private static Iterator<XYPair> solveTrivialCase(int a, int b, int c, int d, int e, int f, long D) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
