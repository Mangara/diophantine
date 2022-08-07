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

import java.util.Iterator;

/**
 * Solves linear Diophantine equations.
 * <p>
 * This class allows you to solve Diophantine equations of the form
 * 
 * \[d x + e y + f = 0\]
 * 
 * where both d and e are non-zero.
 * 
 * The method is based on https://www.alpertron.com.ar/METHODS.HTM#Linear
 */
public class LinearSolver {

    /**
     * Solves the linear Diophantine equation d x + e y + f = 0. Both d and e
     * must be non-zero.
     * @param d
     * @param e
     * @param f
     * @return an iterator over all integer solutions (x, y)
     * @throws IllegalArgumentException if d or e are zero.
     */
    public static Iterator<XYPair> solve(int d, int e, int f) {
        if (d == 0 || e == 0) {
            throw new IllegalArgumentException("d and e should be non-zero.");
        }
        
        return null;
    }
}
