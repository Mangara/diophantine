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

import com.github.mangara.diophantine.TestUtils;
import org.junit.jupiter.api.Test;

public class ParabolicSolverTest {
    
    public ParabolicSolverTest() {
    }

    @Test
    public void test04() {
        System.out.println("4: 9x^2 - 12xy + 4y^2 + 3x + 2y - 12 = 0 (D = 0)");
        int a = 9, b = -12, c = 4, d = 3, e = 2, f = -12;

        long[][] expectedSolutions = new long[][]{
            new long[]{2, 3},
            new long[]{0, -2},
            new long[]{1, 0},
            new long[]{-5, -11},
            new long[]{-24, -30},
            new long[]{-98, -135},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { ParabolicSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test07() {
        System.out.println("7: x^2 + 2xy + y^2 + x + y - 6 = 0 (D = 0)");
        int a = 1, b = 2, c = 1, d = 1, e = 1, f = -6;

        long[][] expectedSolutions = new long[][]{
            new long[]{0, 2},
            new long[]{0, -3},
            new long[]{-1, 3},
            new long[]{-1, -2},
            new long[]{1, 1},
            new long[]{1, -4},
            new long[]{7, -5},
            new long[]{7, -10},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { ParabolicSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
    }
    
    @Test
    public void test20() {
        System.out.println("20: 8x^2 - 24xy + 18y^2 + 5x + 7y + 16 = 0 (D = 0)");
        int a = 8, b = -24, c = 18, d = 5, e = 7, f = 16;

        long[][] expectedSolutions = new long[][]{
            new long[]{-4, -4},
            new long[]{-2, -2},
            new long[]{-137, -83},
            new long[]{-159, -97},
            new long[]{-219, -157},
            new long[]{-193, -139},
            new long[]{-782, -542},
            new long[]{-732, -508},
            new long[]{-1693, -1159},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { ParabolicSolver.solve(a, b, c, d, e, f); });
        //assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
    }

    @Test
    public void test21() {
        System.out.println("21: 8x^2 - 24xy + 18y^2 + 5x + 7y + 16 = 0 (D = 0)");
        int a = 8, b = -24, c = 18, d = 5, e = 7, f = 16;

        long[][] expectedSolutions = new long[][]{
            new long[]{-4, -4},
            new long[]{-2, -2},
            new long[]{-137, -83},
            new long[]{-159, -97},
            new long[]{-219, -157},
            new long[]{-193, -139},
            new long[]{-782, -542},
            new long[]{-732, -508},
            new long[]{-1693, -1159},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { ParabolicSolver.solve(a, b, c, d, e, f); });
        //assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
    }
    
    @Test
    public void test29() {
        System.out.println("29: -7x^2 + 6x - 6y + 222 = 0 (D = 0)");
        int a = -7, b = 0, c = 0, d = 6, e = -6, f = 222;

        long[][] expectedSolutions = new long[][]{
            new long[]{-24, -659},
            new long[]{-18, -359},
            new long[]{-12, -143},
            new long[]{-6, -11},
            new long[]{0, 37},
            new long[]{6, 1},
            new long[]{12, -119},
            new long[]{18, -323},
            new long[]{24, -611},
            new long[]{30, -983},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { ParabolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test34() {
        System.out.println("34: x^2 - 6xy + 9y^2 - 324 = 0 (D = 0)");
        int a = 1, b = -6, c = 9, d = 0, e = 0, f = -324;

        long[][] expectedSolutions = new long[][]{
            new long[]{-99, -39},
            new long[]{-99, -27},
            new long[]{-96, -38},
            new long[]{-96, -26},
            new long[]{-93, -37},
            new long[]{-93, -25},
            new long[]{-90, -36},
            new long[]{-90, -24},
            new long[]{-87, -35},
            new long[]{-87, -23},
            new long[]{-84, -34},
            new long[]{-84, -22},
            new long[]{-81, -33},
            new long[]{-81, -21},
            new long[]{-78, -32},
            new long[]{-78, -20},
            new long[]{-75, -31},
            new long[]{-75, -19},
            new long[]{-72, -30},
            new long[]{-72, -18},
            new long[]{-69, -29},
            new long[]{-69, -17},
            new long[]{-66, -28},
            new long[]{-66, -16},
            new long[]{-63, -27},
            new long[]{-63, -15},
            new long[]{-60, -26},
            new long[]{-60, -14},
            new long[]{-57, -25},
            new long[]{-57, -13},
            new long[]{-54, -24},
            new long[]{-54, -12},
            new long[]{-51, -23},
            new long[]{-51, -11},
            new long[]{-48, -22},
            new long[]{-48, -10},
            new long[]{-45, -21},
            new long[]{-45, -9},
            new long[]{-42, -20},
            new long[]{-42, -8},
            new long[]{-39, -19},
            new long[]{-39, -7},
            new long[]{-36, -18},
            new long[]{-36, -6},
            new long[]{-33, -17},
            new long[]{-33, -5},
            new long[]{-30, -16},
            new long[]{-30, -4},
            new long[]{-27, -15},
            new long[]{-27, -3},
            new long[]{-24, -14},
            new long[]{-24, -2},
            new long[]{-21, -13},
            new long[]{-21, -1},
            new long[]{-18, -12},
            new long[]{-18, 0},
            new long[]{-15, -11},
            new long[]{-15, 1},
            new long[]{-12, -10},
            new long[]{-12, 2},
            new long[]{-9, -9},
            new long[]{-9, 3},
            new long[]{-6, -8},
            new long[]{-6, 4},
            new long[]{-3, -7},
            new long[]{-3, 5},
            new long[]{0, -6},
            new long[]{0, 6},
            new long[]{3, -5},
            new long[]{3, 7},
            new long[]{6, -4},
            new long[]{6, 8},
            new long[]{9, -3},
            new long[]{9, 9},
            new long[]{12, -2},
            new long[]{12, 10},
            new long[]{15, -1},
            new long[]{15, 11},
            new long[]{18, 0},
            new long[]{18, 12},
            new long[]{21, 1},
            new long[]{21, 13},
            new long[]{24, 2},
            new long[]{24, 14},
            new long[]{27, 3},
            new long[]{27, 15},
            new long[]{30, 4},
            new long[]{30, 16},
            new long[]{33, 5},
            new long[]{33, 17},
            new long[]{36, 6},
            new long[]{36, 18},
            new long[]{39, 7},
            new long[]{39, 19},
            new long[]{42, 8},
            new long[]{42, 20},
            new long[]{45, 9},
            new long[]{45, 21},
            new long[]{48, 10},
            new long[]{48, 22},
            new long[]{51, 11},
            new long[]{51, 23},
            new long[]{54, 12},
            new long[]{54, 24},
            new long[]{57, 13},
            new long[]{57, 25},
            new long[]{60, 14},
            new long[]{60, 26},
            new long[]{63, 15},
            new long[]{63, 27},
            new long[]{66, 16},
            new long[]{66, 28},
            new long[]{69, 17},
            new long[]{69, 29},
            new long[]{72, 18},
            new long[]{72, 30},
            new long[]{75, 19},
            new long[]{75, 31},
            new long[]{78, 20},
            new long[]{78, 32},
            new long[]{81, 21},
            new long[]{81, 33},
            new long[]{84, 22},
            new long[]{84, 34},
            new long[]{87, 23},
            new long[]{87, 35},
            new long[]{90, 24},
            new long[]{90, 36},
            new long[]{93, 25},
            new long[]{93, 37},
            new long[]{96, 26},
            new long[]{96, 38},
            new long[]{99, 27},
            new long[]{99, 39},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { ParabolicSolver.solve(a, b, c, d, e, f); });
        //assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
    }
}
