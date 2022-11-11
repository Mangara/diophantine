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

import io.github.mangara.diophantine.TestUtils;
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test50() {
        System.out.println("50: 4y^2 + 2x - 5y - 124 = 0 (D = 0)");
        int a = 0, b = 0, c = 4, d = 2, e = -5, f = -124;

        long[][] expectedSolutions = new long[][]{
            new long[]{-961, -22},
            new long[]{-788, -20},
            new long[]{-631, -18},
            new long[]{-490, -16},
            new long[]{-365, -14},
            new long[]{-256, -12},
            new long[]{-163, -10},
            new long[]{-86, -8},
            new long[]{-25, -6},
            new long[]{20, -4},
            new long[]{49, -2},
            new long[]{62, 0},
            new long[]{59, 2},
            new long[]{40, 4},
            new long[]{5, 6},
            new long[]{-46, 8},
            new long[]{-113, 10},
            new long[]{-196, 12},
            new long[]{-295, 14},
            new long[]{-410, 16},
            new long[]{-541, 18},
            new long[]{-688, 20},
            new long[]{-851, 22},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test51() {
        System.out.println("51: 9x^2 + 6xy + y^2 + 6x + 2y + 1 = 0 (D = 0)");
        int a = 9, b = 6, c = 1, d = 6, e = 2, f = 1;

        long[][] expectedSolutions = new long[][]{
            new long[]{33, -100},
            new long[]{32, -97},
            new long[]{31, -94},
            new long[]{30, -91},
            new long[]{29, -88},
            new long[]{28, -85},
            new long[]{27, -82},
            new long[]{26, -79},
            new long[]{25, -76},
            new long[]{24, -73},
            new long[]{23, -70},
            new long[]{22, -67},
            new long[]{21, -64},
            new long[]{20, -61},
            new long[]{19, -58},
            new long[]{18, -55},
            new long[]{17, -52},
            new long[]{16, -49},
            new long[]{15, -46},
            new long[]{14, -43},
            new long[]{13, -40},
            new long[]{12, -37},
            new long[]{11, -34},
            new long[]{10, -31},
            new long[]{9, -28},
            new long[]{8, -25},
            new long[]{7, -22},
            new long[]{6, -19},
            new long[]{5, -16},
            new long[]{4, -13},
            new long[]{3, -10},
            new long[]{2, -7},
            new long[]{1, -4},
            new long[]{0, -1},
            new long[]{-1, 2},
            new long[]{-2, 5},
            new long[]{-3, 8},
            new long[]{-4, 11},
            new long[]{-5, 14},
            new long[]{-6, 17},
            new long[]{-7, 20},
            new long[]{-8, 23},
            new long[]{-9, 26},
            new long[]{-10, 29},
            new long[]{-11, 32},
            new long[]{-12, 35},
            new long[]{-13, 38},
            new long[]{-14, 41},
            new long[]{-15, 44},
            new long[]{-16, 47},
            new long[]{-17, 50},
            new long[]{-18, 53},
            new long[]{-19, 56},
            new long[]{-20, 59},
            new long[]{-21, 62},
            new long[]{-22, 65},
            new long[]{-23, 68},
            new long[]{-24, 71},
            new long[]{-25, 74},
            new long[]{-26, 77},
            new long[]{-27, 80},
            new long[]{-28, 83},
            new long[]{-29, 86},
            new long[]{-30, 89},
            new long[]{-31, 92},
            new long[]{-32, 95},
            new long[]{-33, 98},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, ParabolicSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test52() {
        System.out.println("52: 3x^2 + 6xy + 3y^2 + 7x + 7y + 9 = 0 (D = 0)");
        int a = 3, b = 6, c = 3, d = 7, e = 7, f = 9;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, ParabolicSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test53() {
        System.out.println("53: 3x^2 + 6xy + 3y^2 + 7x + 7y + 1 = 0 (D = 0)");
        int a = 3, b = 6, c = 3, d = 7, e = 7, f = 1;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, ParabolicSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test54() {
        System.out.println("54: 2x^2 + 4xy + 2y^2 + 6x + 3y + 5 = 0 (D = 0)");
        int a = 2, b = 4, c = 2, d = 6, e = 3, f = 5;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, ParabolicSolver.solve(a, b, c, d, e, f));
    }
}
