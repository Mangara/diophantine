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
import java.util.Iterator;

/**
 * Solves elliptical quadratic Diophantine equations in two variables.
 * <p>
 * The method is based on K. R. Matthews, "Solving the Diophantine equation \(ax^2 + bxy + cy^2 + dx + ey + f = 0\)", http://www.numbertheory.org/PDFS/general_quadratic_solution.pdf
 */
public class EllipticalSolver {

    /**
     * Solves the quadratic Diophantine equation 
     * a x^2 + b xy + c y^2 + d x + e y + f = 0,
     * given that D = b^2 - 4ac < 0 and not a perfect square.
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
        // Use Legendre's transform to a X^2 + b XY + c Y^2 = k
        // Solve restricted
        // For each solution (X, Y), if D|(X + alpha) and D|(Y + beta)
        // then ((X + alpha)/D, (Y + beta)/D) is a solution
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
