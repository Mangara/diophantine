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

public class SquareDiscriminantSolverTest {

    public SquareDiscriminantSolverTest() {
    }

    @Test
    public void test03() {
        System.out.println("3: 3xy + 2y^2 - 4x - 3y - 12 = 0 (D > 0)");
        int a = 0, b = 3, c = 2, d = -4, e = -3, f = -12;

        long[][] expectedSolutions = new long[][]{
            new long[]{5, 2},
            new long[]{-3, 6},
            new long[]{-1, 4},
            new long[]{-13, 20},
            new long[]{-13, 1},
            new long[]{-1, -1},
            new long[]{-3, 0},
            new long[]{5, -8},
            new long[]{2, -4},
            new long[]{24, -36},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test08() {
        System.out.println("8: 2x^2 + 7xy + 6y^2 + 5x + 7y - 27 = 0 (D > 0)");
        int a = 2, b = 7, c = 6, d = 5, e = 7, f = -27;

        long[][] expectedSolutions = new long[][]{
            new long[]{-59, 40},
            new long[]{-34, 15},
            new long[]{-21, 15},
            new long[]{-7, 1},
            new long[]{-7, 6},
            new long[]{1, 1},
            new long[]{4, -5},
            new long[]{11, -9},
            new long[]{11, -5},
            new long[]{18, -9},
            new long[]{21, -15},
            new long[]{29, -20},
            new long[]{29, -15},
            new long[]{43, -29},
            new long[]{56, -29},
            new long[]{81, -54},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test18() {
        System.out.println("18: 2xy + 5x + 56y + 7 = 0 (D > 0)");
        int a = 0, b = 2, c = 0, d = 5, e = 56, f = 7;

        long[][] expectedSolutions = new long[][]{
            new long[]{-29, -69},
            new long[]{-35, -12},
            new long[]{-47, -6},
            new long[]{-161, -3},
            new long[]{105, -2},
            new long[]{-9, 1},
            new long[]{-21, 7},
            new long[]{-27, 64},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test24() {
        System.out.println("24: x^2 + 2xy + 4x + 2y + 3 = 0 (D > 0)");
        int a = 1, b = 2, c = 0, d = 4, e = 2, f = 3;

        long[][] expectedSolutions = new long[][]{
            new long[]{1, -2},
            new long[]{3, -3},
            new long[]{11, -7},
            new long[]{-15, 6},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test25() {
        System.out.println("25: xy + 3y^2 - 2x - y - 10 = 0");
        int a = 0, b = 1, c = 3, d = -2, e = -1, f = -10;

        long[][] expectedSolutions = new long[][]{
            new long[]{1, 2},
            new long[]{1, -2},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test27() {
        System.out.println("27: -2xy - 3x - 7y + 162 = 0 (D > 0)");
        int a = 0, b = -2, c = 0, d = -3, e = -7, f = 162;

        long[][] expectedSolutions = new long[][]{
            new long[]{-176, -2},
            new long[]{-61, -3},
            new long[]{-38, -4},
            new long[]{-15, -9},
            new long[]{-11, -13},
            new long[]{-6, -36},
            new long[]{-5, -59},
            new long[]{-4, -174},
            new long[]{-3, 171},
            new long[]{-2, 56},
            new long[]{-1, 33},
            new long[]{4, 10},
            new long[]{8, 6},
            new long[]{31, 1},
            new long[]{54, 0},
            new long[]{169, -1},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test28() {
        System.out.println("28: -8xy - 8x + 7y + 527 = 0 (D > 0)");
        int a = 0, b = -8, c = 0, d = -8, e = 7, f = 527;

        long[][] expectedSolutions = new long[][]{
            new long[]{1, 519},
            new long[]{9, 7},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test45() {
        System.out.println("45: -2x^2 + 3xy - y^2 - 6x - 7y + 90 = 0 (D > 0)");
        int a = -2, b = 3, c = -1, d = -6, e = -7, f = 90;

        long[][] expectedSolutions = new long[][]{
            new long[]{-138, -295},
            new long[]{-138, -126},
            new long[]{-54, -126},
            new long[]{-54, -43},
            new long[]{-6, -27},
            new long[]{-6, 2},
            new long[]{6, 2},
            new long[]{6, 9},
            new long[]{60, 83},
            new long[]{60, 90},
            new long[]{72, 90},
            new long[]{72, 119},
            new long[]{120, 135},
            new long[]{120, 218},
            new long[]{204, 218},
            new long[]{204, 387},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { SquareDiscriminantSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

}
