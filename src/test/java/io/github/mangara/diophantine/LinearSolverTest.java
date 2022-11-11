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
package io.github.mangara.diophantine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinearSolverTest {
    
    public LinearSolverTest() {
    }
    
    @Test
    public void testSolveNoSolutions() {
        System.out.println("solve returns an empty iterator when there are no solutions");
        assertFalse(LinearSolver.solve(6, 9, 2).hasNext());
    }
    
    @Test
    public void test1() {
        System.out.println("1: -9x + y + 29 = 0");
        int d = -9, e = 1, f = 29;

        long[][] expectedSolutions = new long[][]{
            new long[]{-7, -92},
            new long[]{-6, -83},
            new long[]{-5, -74},
            new long[]{-4, -65},
            new long[]{-3, -56},
            new long[]{-2, -47},
            new long[]{-1, -38},
            new long[]{0, -29},
            new long[]{1, -20},
            new long[]{2, -11},
            new long[]{3, -2},
            new long[]{4, 7},
            new long[]{5, 16},
            new long[]{6, 25},
            new long[]{7, 34},
            new long[]{8, 43},
            new long[]{9, 52},
            new long[]{10, 61},
            new long[]{11, 70},
            new long[]{12, 79},
            new long[]{13, 88},
            new long[]{14, 97},
        };

        TestUtils.validateExpectedSolutions(0, 0, 0, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(0, 0, 0, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test2() {
        System.out.println("2: 2x + 8y - 22 = 0");
        int d = 2, e = 8, f = -22;

        long[][] expectedSolutions = new long[][]{
            new long[]{99, -22},
            new long[]{95, -21},
            new long[]{91, -20},
            new long[]{87, -19},
            new long[]{83, -18},
            new long[]{79, -17},
            new long[]{75, -16},
            new long[]{71, -15},
            new long[]{67, -14},
            new long[]{63, -13},
            new long[]{59, -12},
            new long[]{55, -11},
            new long[]{51, -10},
            new long[]{47, -9},
            new long[]{43, -8},
            new long[]{39, -7},
            new long[]{35, -6},
            new long[]{31, -5},
            new long[]{27, -4},
            new long[]{23, -3},
            new long[]{19, -2},
            new long[]{15, -1},
            new long[]{11, 0},
            new long[]{7, 1},
            new long[]{3, 2},
            new long[]{-1, 3},
            new long[]{-5, 4},
            new long[]{-9, 5},
            new long[]{-13, 6},
            new long[]{-17, 7},
            new long[]{-21, 8},
            new long[]{-25, 9},
            new long[]{-29, 10},
            new long[]{-33, 11},
            new long[]{-37, 12},
            new long[]{-41, 13},
            new long[]{-45, 14},
            new long[]{-49, 15},
            new long[]{-53, 16},
            new long[]{-57, 17},
            new long[]{-61, 18},
            new long[]{-65, 19},
            new long[]{-69, 20},
            new long[]{-73, 21},
            new long[]{-77, 22},
            new long[]{-81, 23},
            new long[]{-85, 24},
            new long[]{-89, 25},
            new long[]{-93, 26},
            new long[]{-97, 27},
        };

        TestUtils.validateExpectedSolutions(0, 0, 0, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(0, 0, 0, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test3() {
        System.out.println("3: 570x + 159y - 3009 = 0");
        int d = 570, e = 159, f = -3009;

        long[][] expectedSolutions = new long[][]{
            new long[]{270, -949},
            new long[]{217, -759},
            new long[]{164, -569},
            new long[]{111, -379},
            new long[]{58, -189},
            new long[]{5, 1},
            new long[]{-48, 191},
            new long[]{-101, 381},
            new long[]{-154, 571},
            new long[]{-207, 761},
            new long[]{-260, 951},
        };

        TestUtils.validateExpectedSolutions(0, 0, 0, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(0, 0, 0, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test4() {
        System.out.println("4: 10 x + 84 y + 16 = 0 (linear)");
        int d = 10, e = 84, f = 16;

        long[][] expectedSolutions = new long[][]{
            new long[]{-52, 6},
            new long[]{-94, 11},
            new long[]{-136, 16},
        };

        TestUtils.validateExpectedSolutions(0, 0, 0, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(0, 0, 0, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test5() {
        System.out.println("5: -5x - 4y + 8 = 0 (linear)");
        int d = -5, e = -4, f = 8;

        long[][] expectedSolutions = new long[][]{
            new long[]{40, -48},
            new long[]{36, -43},
            new long[]{32, -38},
            new long[]{28, -33},
            new long[]{24, -28},
            new long[]{20, -23},
            new long[]{16, -18},
            new long[]{12, -13},
            new long[]{8, -8},
            new long[]{4, -3},
            new long[]{0, 2},
            new long[]{-4, 7},
            new long[]{-8, 12},
            new long[]{-12, 17},
            new long[]{-16, 22},
            new long[]{-20, 27},
            new long[]{-24, 32},
            new long[]{-28, 37},
            new long[]{-32, 42},
            new long[]{-36, 47},
        };

        TestUtils.validateExpectedSolutions(0, 0, 0, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(0, 0, 0, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test6() {
        System.out.println("6: -3x + 8 = 0 (linear)");
        int d = -3, e = 0, f = 8;

        TestUtils.assertNoSolutions(0, 0, 0, d, e, f, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test7() {
        System.out.println("7: 5y + 8 = 0 (linear)");
        int d = 0, e = 5, f = 8;

        TestUtils.assertNoSolutions(0, 0, 0, d, e, f, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test8() {
        System.out.println("8: 4x + 8 = 0 (D = 0)");
        int a = 0, b = 0, c = 0, d = 4, e = 0, f = 8;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, -10},
            new long[]{-2, -9},
            new long[]{-2, -8},
            new long[]{-2, -7},
            new long[]{-2, -6},
            new long[]{-2, -5},
            new long[]{-2, -4},
            new long[]{-2, -3},
            new long[]{-2, -2},
            new long[]{-2, -1},
            new long[]{-2, 0},
            new long[]{-2, 1},
            new long[]{-2, 2},
            new long[]{-2, 3},
            new long[]{-2, 4},
            new long[]{-2, 5},
            new long[]{-2, 6},
            new long[]{-2, 7},
            new long[]{-2, 8},
            new long[]{-2, 9},
            new long[]{-2, 10},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test9() {
        System.out.println("9: 3y + 21 = 0 (D = 0)");
        int a = 0, b = 0, c = 0, d = 0, e = 3, f = 21;

        long[][] expectedSolutions = new long[][]{
            new long[]{-10, -7},
            new long[]{-9, -7},
            new long[]{-8, -7},
            new long[]{-7, -7},
            new long[]{-6, -7},
            new long[]{-5, -7},
            new long[]{-4, -7},
            new long[]{-3, -7},
            new long[]{-2, -7},
            new long[]{-1, -7},
            new long[]{0, -7},
            new long[]{1, -7},
            new long[]{2, -7},
            new long[]{3, -7},
            new long[]{4, -7},
            new long[]{5, -7},
            new long[]{6, -7},
            new long[]{7, -7},
            new long[]{8, -7},
            new long[]{9, -7},
            new long[]{10, -7},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test10() {
        System.out.println("10: 8 = 0 (linear)");
        int d = 0, e = 0, f = 8;

        TestUtils.assertNoSolutions(0, 0, 0, d, e, f, LinearSolver.solve(d, e, f));
    }
    
    @Test
    public void test11() {
        System.out.println("11: 0 = 0 (D = 0)");
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;

        long[][] expectedSolutions = new long[][]{
            new long[]{-3, -3},
            new long[]{-2, -3},
            new long[]{-1, -3},
            new long[]{0, -3},
            new long[]{1, -3},
            new long[]{2, -3},
            new long[]{3, -3},
            new long[]{-3, -2},
            new long[]{-2, -2},
            new long[]{-1, -2},
            new long[]{0, -2},
            new long[]{1, -2},
            new long[]{2, -2},
            new long[]{3, -2},
            new long[]{-3, -1},
            new long[]{-2, -1},
            new long[]{-1, -1},
            new long[]{0, -1},
            new long[]{1, -1},
            new long[]{2, -1},
            new long[]{3, -1},
            new long[]{-3, 0},
            new long[]{-2, 0},
            new long[]{-1, 0},
            new long[]{0, 0},
            new long[]{1, 0},
            new long[]{2, 0},
            new long[]{3, 0},
            new long[]{-3, 1},
            new long[]{-2, 1},
            new long[]{-1, 1},
            new long[]{0, 1},
            new long[]{1, 1},
            new long[]{2, 1},
            new long[]{3, 1},
            new long[]{-3, 2},
            new long[]{-2, 2},
            new long[]{-1, 2},
            new long[]{0, 2},
            new long[]{1, 2},
            new long[]{2, 2},
            new long[]{3, 2},
            new long[]{-3, 3},
            new long[]{-2, 3},
            new long[]{-1, 3},
            new long[]{0, 3},
            new long[]{1, 3},
            new long[]{2, 3},
            new long[]{3, 3},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, LinearSolver.solve(d, e, f));
    }
}
