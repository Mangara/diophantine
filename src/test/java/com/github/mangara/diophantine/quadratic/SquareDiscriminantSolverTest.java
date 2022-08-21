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
            new long[]{-1, -11},
            new long[]{19, -11},
            new long[]{-1, -10},
            new long[]{17, -10},
            new long[]{-1, -9},
            new long[]{15, -9},
            new long[]{-1, -8},
            new long[]{13, -8},
            new long[]{-1, -7},
            new long[]{11, -7},
            new long[]{-1, -6},
            new long[]{9, -6},
            new long[]{-1, -5},
            new long[]{7, -5},
            new long[]{-1, -4},
            new long[]{5, -4},
            new long[]{-1, -3},
            new long[]{3, -3},
            new long[]{-1, -2},
            new long[]{1, -2},
            new long[]{-1, -1},
            new long[]{-3, 0},
            new long[]{-1, 0},
            new long[]{-5, 1},
            new long[]{-1, 1},
            new long[]{-7, 2},
            new long[]{-1, 2},
            new long[]{-9, 3},
            new long[]{-1, 3},
            new long[]{-11, 4},
            new long[]{-1, 4},
            new long[]{-13, 5},
            new long[]{-1, 5},
            new long[]{-15, 6},
            new long[]{-1, 6},
            new long[]{-17, 7},
            new long[]{-1, 7},
            new long[]{-19, 8},
            new long[]{-1, 8},
            new long[]{-1, 9},
            new long[]{-1, 10},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
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
    
    @Test
    public void test55() {
        System.out.println("55: -5x^2 - 4xy + 3x - 4y + 8 = 0 (D > 0)");
        int a = -5, b = -4, c = 0, d = 3, e = -4, f = 8;

        long[][] expectedSolutions = new long[][]{
            new long[]{-1, -100},
            new long[]{-1, -99},
            new long[]{-1, -98},
            new long[]{80, -98},
            new long[]{-1, -97},
            new long[]{-1, -96},
            new long[]{-1, -95},
            new long[]{-1, -94},
            new long[]{-1, -93},
            new long[]{76, -93},
            new long[]{-1, -92},
            new long[]{-1, -91},
            new long[]{-1, -90},
            new long[]{-1, -89},
            new long[]{-1, -88},
            new long[]{72, -88},
            new long[]{-1, -87},
            new long[]{-1, -86},
            new long[]{-1, -85},
            new long[]{-1, -84},
            new long[]{-1, -83},
            new long[]{68, -83},
            new long[]{-1, -82},
            new long[]{-1, -81},
            new long[]{-1, -80},
            new long[]{-1, -79},
            new long[]{-1, -78},
            new long[]{64, -78},
            new long[]{-1, -77},
            new long[]{-1, -76},
            new long[]{-1, -75},
            new long[]{-1, -74},
            new long[]{-1, -73},
            new long[]{60, -73},
            new long[]{-1, -72},
            new long[]{-1, -71},
            new long[]{-1, -70},
            new long[]{-1, -69},
            new long[]{-1, -68},
            new long[]{56, -68},
            new long[]{-1, -67},
            new long[]{-1, -66},
            new long[]{-1, -65},
            new long[]{-1, -64},
            new long[]{-1, -63},
            new long[]{52, -63},
            new long[]{-1, -62},
            new long[]{-1, -61},
            new long[]{-1, -60},
            new long[]{-1, -59},
            new long[]{-1, -58},
            new long[]{48, -58},
            new long[]{-1, -57},
            new long[]{-1, -56},
            new long[]{-1, -55},
            new long[]{-1, -54},
            new long[]{-1, -53},
            new long[]{44, -53},
            new long[]{-1, -52},
            new long[]{-1, -51},
            new long[]{-1, -50},
            new long[]{-1, -49},
            new long[]{-1, -48},
            new long[]{40, -48},
            new long[]{-1, -47},
            new long[]{-1, -46},
            new long[]{-1, -45},
            new long[]{-1, -44},
            new long[]{-1, -43},
            new long[]{36, -43},
            new long[]{-1, -42},
            new long[]{-1, -41},
            new long[]{-1, -40},
            new long[]{-1, -39},
            new long[]{-1, -38},
            new long[]{32, -38},
            new long[]{-1, -37},
            new long[]{-1, -36},
            new long[]{-1, -35},
            new long[]{-1, -34},
            new long[]{-1, -33},
            new long[]{28, -33},
            new long[]{-1, -32},
            new long[]{-1, -31},
            new long[]{-1, -30},
            new long[]{-1, -29},
            new long[]{-1, -28},
            new long[]{24, -28},
            new long[]{-1, -27},
            new long[]{-1, -26},
            new long[]{-1, -25},
            new long[]{-1, -24},
            new long[]{-1, -23},
            new long[]{20, -23},
            new long[]{-1, -22},
            new long[]{-1, -21},
            new long[]{-1, -20},
            new long[]{-1, -19},
            new long[]{-1, -18},
            new long[]{16, -18},
            new long[]{-1, -17},
            new long[]{-1, -16},
            new long[]{-1, -15},
            new long[]{-1, -14},
            new long[]{-1, -13},
            new long[]{12, -13},
            new long[]{-1, -12},
            new long[]{-1, -11},
            new long[]{-1, -10},
            new long[]{-1, -9},
            new long[]{-1, -8},
            new long[]{8, -8},
            new long[]{-1, -7},
            new long[]{-1, -6},
            new long[]{-1, -5},
            new long[]{-1, -4},
            new long[]{-1, -3},
            new long[]{4, -3},
            new long[]{-1, -2},
            new long[]{-1, -1},
            new long[]{-1, 0},
            new long[]{-1, 1},
            new long[]{-1, 2},
            new long[]{0, 2},
            new long[]{-1, 3},
            new long[]{-1, 4},
            new long[]{-1, 5},
            new long[]{-1, 6},
            new long[]{-4, 7},
            new long[]{-1, 7},
            new long[]{-1, 8},
            new long[]{-1, 9},
            new long[]{-1, 10},
            new long[]{-1, 11},
            new long[]{-8, 12},
            new long[]{-1, 12},
            new long[]{-1, 13},
            new long[]{-1, 14},
            new long[]{-1, 15},
            new long[]{-1, 16},
            new long[]{-12, 17},
            new long[]{-1, 17},
            new long[]{-1, 18},
            new long[]{-1, 19},
            new long[]{-1, 20},
            new long[]{-1, 21},
            new long[]{-16, 22},
            new long[]{-1, 22},
            new long[]{-1, 23},
            new long[]{-1, 24},
            new long[]{-1, 25},
            new long[]{-1, 26},
            new long[]{-20, 27},
            new long[]{-1, 27},
            new long[]{-1, 28},
            new long[]{-1, 29},
            new long[]{-1, 30},
            new long[]{-1, 31},
            new long[]{-24, 32},
            new long[]{-1, 32},
            new long[]{-1, 33},
            new long[]{-1, 34},
            new long[]{-1, 35},
            new long[]{-1, 36},
            new long[]{-28, 37},
            new long[]{-1, 37},
            new long[]{-1, 38},
            new long[]{-1, 39},
            new long[]{-1, 40},
            new long[]{-1, 41},
            new long[]{-32, 42},
            new long[]{-1, 42},
            new long[]{-1, 43},
            new long[]{-1, 44},
            new long[]{-1, 45},
            new long[]{-1, 46},
            new long[]{-36, 47},
            new long[]{-1, 47},
            new long[]{-1, 48},
            new long[]{-1, 49},
            new long[]{-1, 50},
            new long[]{-1, 51},
            new long[]{-40, 52},
            new long[]{-1, 52},
            new long[]{-1, 53},
            new long[]{-1, 54},
            new long[]{-1, 55},
            new long[]{-1, 56},
            new long[]{-44, 57},
            new long[]{-1, 57},
            new long[]{-1, 58},
            new long[]{-1, 59},
            new long[]{-1, 60},
            new long[]{-1, 61},
            new long[]{-48, 62},
            new long[]{-1, 62},
            new long[]{-1, 63},
            new long[]{-1, 64},
            new long[]{-1, 65},
            new long[]{-1, 66},
            new long[]{-52, 67},
            new long[]{-1, 67},
            new long[]{-1, 68},
            new long[]{-1, 69},
            new long[]{-1, 70},
            new long[]{-1, 71},
            new long[]{-56, 72},
            new long[]{-1, 72},
            new long[]{-1, 73},
            new long[]{-1, 74},
            new long[]{-1, 75},
            new long[]{-1, 76},
            new long[]{-60, 77},
            new long[]{-1, 77},
            new long[]{-1, 78},
            new long[]{-1, 79},
            new long[]{-1, 80},
            new long[]{-1, 81},
            new long[]{-64, 82},
            new long[]{-1, 82},
            new long[]{-1, 83},
            new long[]{-1, 84},
            new long[]{-1, 85},
            new long[]{-1, 86},
            new long[]{-68, 87},
            new long[]{-1, 87},
            new long[]{-1, 88},
            new long[]{-1, 89},
            new long[]{-1, 90},
            new long[]{-1, 91},
            new long[]{-72, 92},
            new long[]{-1, 92},
            new long[]{-1, 93},
            new long[]{-1, 94},
            new long[]{-1, 95},
            new long[]{-1, 96},
            new long[]{-76, 97},
            new long[]{-1, 97},
            new long[]{-1, 98},
            new long[]{-1, 99},
            new long[]{-1, 100},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test56() {
        System.out.println("56: 2x^2 + 3xy - 2y^2 - 2x - 4y = 0 (D > 0)");
        int a = 2, b = 3, c = -2, d = -2, e = -4, f = 0;

        long[][] expectedSolutions = new long[][]{
            new long[]{-49, -100},
            new long[]{-48, -98},
            new long[]{-47, -96},
            new long[]{-46, -94},
            new long[]{-45, -92},
            new long[]{-44, -90},
            new long[]{-43, -88},
            new long[]{-42, -86},
            new long[]{-41, -84},
            new long[]{-40, -82},
            new long[]{-39, -80},
            new long[]{-38, -78},
            new long[]{-37, -76},
            new long[]{-36, -74},
            new long[]{-35, -72},
            new long[]{-34, -70},
            new long[]{-33, -68},
            new long[]{-32, -66},
            new long[]{-31, -64},
            new long[]{-30, -62},
            new long[]{-29, -60},
            new long[]{-28, -58},
            new long[]{-27, -56},
            new long[]{-26, -54},
            new long[]{-25, -52},
            new long[]{-24, -50},
            new long[]{100, -50},
            new long[]{98, -49},
            new long[]{-23, -48},
            new long[]{96, -48},
            new long[]{94, -47},
            new long[]{-22, -46},
            new long[]{92, -46},
            new long[]{90, -45},
            new long[]{-21, -44},
            new long[]{88, -44},
            new long[]{86, -43},
            new long[]{-20, -42},
            new long[]{84, -42},
            new long[]{82, -41},
            new long[]{-19, -40},
            new long[]{80, -40},
            new long[]{78, -39},
            new long[]{-18, -38},
            new long[]{76, -38},
            new long[]{74, -37},
            new long[]{-17, -36},
            new long[]{72, -36},
            new long[]{70, -35},
            new long[]{-16, -34},
            new long[]{68, -34},
            new long[]{66, -33},
            new long[]{-15, -32},
            new long[]{64, -32},
            new long[]{62, -31},
            new long[]{-14, -30},
            new long[]{60, -30},
            new long[]{58, -29},
            new long[]{-13, -28},
            new long[]{56, -28},
            new long[]{54, -27},
            new long[]{-12, -26},
            new long[]{52, -26},
            new long[]{50, -25},
            new long[]{-11, -24},
            new long[]{48, -24},
            new long[]{46, -23},
            new long[]{-10, -22},
            new long[]{44, -22},
            new long[]{42, -21},
            new long[]{-9, -20},
            new long[]{40, -20},
            new long[]{38, -19},
            new long[]{-8, -18},
            new long[]{36, -18},
            new long[]{34, -17},
            new long[]{-7, -16},
            new long[]{32, -16},
            new long[]{30, -15},
            new long[]{-6, -14},
            new long[]{28, -14},
            new long[]{26, -13},
            new long[]{-5, -12},
            new long[]{24, -12},
            new long[]{22, -11},
            new long[]{-4, -10},
            new long[]{20, -10},
            new long[]{18, -9},
            new long[]{-3, -8},
            new long[]{16, -8},
            new long[]{14, -7},
            new long[]{-2, -6},
            new long[]{12, -6},
            new long[]{10, -5},
            new long[]{-1, -4},
            new long[]{8, -4},
            new long[]{6, -3},
            new long[]{0, -2},
            new long[]{4, -2},
            new long[]{2, -1},
            new long[]{0, 0},
            new long[]{1, 0},
            new long[]{-2, 1},
            new long[]{-4, 2},
            new long[]{2, 2},
            new long[]{-6, 3},
            new long[]{-8, 4},
            new long[]{3, 4},
            new long[]{-10, 5},
            new long[]{-12, 6},
            new long[]{4, 6},
            new long[]{-14, 7},
            new long[]{-16, 8},
            new long[]{5, 8},
            new long[]{-18, 9},
            new long[]{-20, 10},
            new long[]{6, 10},
            new long[]{-22, 11},
            new long[]{-24, 12},
            new long[]{7, 12},
            new long[]{-26, 13},
            new long[]{-28, 14},
            new long[]{8, 14},
            new long[]{-30, 15},
            new long[]{-32, 16},
            new long[]{9, 16},
            new long[]{-34, 17},
            new long[]{-36, 18},
            new long[]{10, 18},
            new long[]{-38, 19},
            new long[]{-40, 20},
            new long[]{11, 20},
            new long[]{-42, 21},
            new long[]{-44, 22},
            new long[]{12, 22},
            new long[]{-46, 23},
            new long[]{-48, 24},
            new long[]{13, 24},
            new long[]{-50, 25},
            new long[]{-52, 26},
            new long[]{14, 26},
            new long[]{-54, 27},
            new long[]{-56, 28},
            new long[]{15, 28},
            new long[]{-58, 29},
            new long[]{-60, 30},
            new long[]{16, 30},
            new long[]{-62, 31},
            new long[]{-64, 32},
            new long[]{17, 32},
            new long[]{-66, 33},
            new long[]{-68, 34},
            new long[]{18, 34},
            new long[]{-70, 35},
            new long[]{-72, 36},
            new long[]{19, 36},
            new long[]{-74, 37},
            new long[]{-76, 38},
            new long[]{20, 38},
            new long[]{-78, 39},
            new long[]{-80, 40},
            new long[]{21, 40},
            new long[]{-82, 41},
            new long[]{-84, 42},
            new long[]{22, 42},
            new long[]{-86, 43},
            new long[]{-88, 44},
            new long[]{23, 44},
            new long[]{-90, 45},
            new long[]{-92, 46},
            new long[]{24, 46},
            new long[]{-94, 47},
            new long[]{-96, 48},
            new long[]{25, 48},
            new long[]{-98, 49},
            new long[]{-100, 50},
            new long[]{26, 50},
            new long[]{27, 52},
            new long[]{28, 54},
            new long[]{29, 56},
            new long[]{30, 58},
            new long[]{31, 60},
            new long[]{32, 62},
            new long[]{33, 64},
            new long[]{34, 66},
            new long[]{35, 68},
            new long[]{36, 70},
            new long[]{37, 72},
            new long[]{38, 74},
            new long[]{39, 76},
            new long[]{40, 78},
            new long[]{41, 80},
            new long[]{42, 82},
            new long[]{43, 84},
            new long[]{44, 86},
            new long[]{45, 88},
            new long[]{46, 90},
            new long[]{47, 92},
            new long[]{48, 94},
            new long[]{49, 96},
            new long[]{50, 98},
            new long[]{51, 100},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, SquareDiscriminantSolver.solve(a, b, c, d, e, f));
    }

}
