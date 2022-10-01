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

public class HyperbolicSolverTest {
    
    public HyperbolicSolverTest() {
    }

    @Test
    public void test01() {
        System.out.println("1: x^2 - 15y^2 - 61 = 0 (D > 0)");
        int a = 1, b = 0, c = -15, d = 0, e = 0, f = -61;

        long[][] expectedSolutions = new long[][]{
            new long[]{74, 19},
            new long[]{-74, 19},
            new long[]{74, -19},
            new long[]{-74, -19},
            new long[]{-581, 150},
            new long[]{-4574, 1181},
            new long[]{-36011, 9298},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test05() {
        System.out.println("5: x^2 - 8xy - 17y^2 + 72y - 75 = 0 (D > 0)");
        int a = 1, b = -8, c = -17, d = 0, e = 72, f = -75;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, 1},
            new long[]{-250, -25},
            new long[]{-11690, -1199},
            new long[]{-537682, -55177},
            new long[]{-34, 23},
            new long[]{10, 1},
            new long[]{218, 23},
            new long[]{50, -25},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test06() {
        System.out.println("6: x^2 + 8xy + y^2 + 2x - 4y + 1 = 0 (D > 0)");
        int a = 1, b = 8, c = 1, d = 2, e = -4, f = 1;

        long[][] expectedSolutions = new long[][]{
            new long[]{-1, 0},
            new long[]{-1, 12},
            new long[]{-97, 768},
            new long[]{-6049, 47628},
            new long[]{-97, 12},
            new long[]{1, -2},
            new long[]{13, -2},
            new long[]{13, -98},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test11() {
        System.out.println("11: 3x^2 - 22xy + 25y^2 - 81 = 0 (D > 0)");
        int a = 3, b = -22, c = 25, d = 0, e = 0, f = -81;

        long[][] expectedSolutions = new long[][]{
            new long[]{-54, -9},
            new long[]{-12, -9},
            new long[]{-2, 1},
            new long[]{2, -1},
            new long[]{12, 9},
            new long[]{54, 9},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test13() {
        System.out.println("13: 41x^2 + 25xy - 125y^2 - 10x + 9y - 7 = 0 (D > 0)");
        int a = 41, b = 25, c = -125, d = -10, e = 9, f = -7;

        long[][] expectedSolutions = new long[][]{
            new long[]{3, 2},
            new long[]{-1224927844935779L, -834638485983846L},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test14() {
        System.out.println("14: 3x^2 + 14xy + 6y^2 - 17x - 23y - 505 = 0 (D > 0)");
        int a = 3, b = 14, c = 6, d = -17, e = -23, f = -505;

        long[][] expectedSolutions = new long[][]{
            new long[]{610, -1275},
            new long[]{-31, 7},
            new long[]{-1291, 2707},
            new long[]{70, -15},
            new long[]{-125, 265},
            new long[]{4, 7},
            new long[]{211015, -441995},
            new long[]{20605, -43157},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test15() {
        System.out.println("15: 6x^2 + 24xy + 12y^2 + x - y + 7 = 0 (D > 0)");
        int a = 6, b = 24, c = 12, d = 1, e = -1, f = 7;

        long[][] expectedSolutions = new long[][]{
            new long[]{2, -3},
            new long[]{13, -22},
            new long[]{27, -8},
            new long[]{208, -61},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test16() {
        System.out.println("16: 6x^2 + 30xy + 18y^2 + x - y - 11 = 0 (D > 0)");
        int a = 6, b = 30, c = 18, d = 1, e = -1, f = -11;

        long[][] expectedSolutions = new long[][]{
            new long[]{2, -3},
            new long[]{7, -10},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test19() {
        System.out.println("19: 42x^2 + 8xy + 15y^2 + 23x + 17y - 4915 = 0 (D < 0)");
        int a = 42, b = 8, c = 15, d = 23, e = 17, f = -4915;

        long[][] expectedSolutions = new long[][]{
            new long[]{-11, -1},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        System.out.println("Skipping until the UnaryCongruenceSolver is faster - see https://github.com/Mangara/diophantine/issues/1");
        //TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test22() {
        System.out.println("22: 2x^2 + 5xy + y^2 + 1 = 0 (D > 0)");
        int a = 2, b = 5, c = 1, d = 0, e = 0, f = 1;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, 1},
            new long[]{2, -1},
            new long[]{-2, 9},
            new long[]{2, -9},
            new long[]{-130, 57},
            new long[]{-130, 593},
            new long[]{130, -593},
            new long[]{130, -57},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test31() {
        System.out.println("31: 2x^2 - 9y^2 + 601 = 0 (D > 0)");
        int a = 2, b = 0, c = -9, d = 0, e = 0, f = 601;

        long[][] expectedSolutions = new long[][]{
            new long[]{-460, -217},
            new long[]{-460, 217},
            new long[]{-188, -89},
            new long[]{-188, 89},
            new long[]{-8, -9},
            new long[]{-8, 9},
            new long[]{8, -9},
            new long[]{8, 9},
            new long[]{188, -89},
            new long[]{188, 89},
            new long[]{460, -217},
            new long[]{460, 217},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test32() {
        System.out.println("32: -3x^2 - 8xy + 2y^2 - 87 = 0 (D > 0)");
        int a = -3, b = -8, c = 2, d = 0, e = 0, f = -87;

        long[][] expectedSolutions = new long[][]{
            new long[]{-785, 271},
            new long[]{-391, 135},
            new long[]{-31, -135},
            new long[]{-31, 11},
            new long[]{-25, -109},
            new long[]{-25, 9},
            new long[]{-1, -9},
            new long[]{-1, 5},
            new long[]{1, -5},
            new long[]{1, 9},
            new long[]{25, -9},
            new long[]{25, 109},
            new long[]{31, -11},
            new long[]{31, 135},
            new long[]{391, -135},
            new long[]{785, -271},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test35() {
        System.out.println("35: 8x^2 + 8xy + y^2 - 9x + 3y - 292 = 0 (D > 0)");
        int a = 8, b = 8, c = 1, d = -9, e = 3, f = -292;

        long[][] expectedSolutions = new long[][]{
            new long[]{-46, 55},
            new long[]{-46, 310},
            new long[]{4, -40},
            new long[]{4, 5},
            new long[]{224, -260},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test37() {
        System.out.println("37: 7x^2 - xy - 4y^2 - 5x - 9y - 50 = 0 (D > 0)");
        int a = 7, b = -1, c = -4, d = -5, e = -9, f = -50;

        long[][] expectedSolutions = new long[][]{
            new long[]{-351, -424},
            new long[]{-156, 226},
            new long[]{-35, 50},
            new long[]{-6, -8},
            new long[]{-3, 2},
            new long[]{4, 2},
            new long[]{189, 226},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test39() {
        System.out.println("39: x^2 + 2xy - 4y^2 + x + 2y - 2 = 0 (D > 0)");
        int a = 1, b = 2, c = -4, d = 1, e = 2, f = -2;

        long[][] expectedSolutions = new long[][]{
            new long[]{-7207, -5830},
            new long[]{-2039, -1649},
            new long[]{5336, -1649},
            new long[]{-154, -124},
            new long[]{401, -124},
            new long[]{-44, -35},
            new long[]{113, -35},
            new long[]{-23, -18},
            new long[]{58, -18},
            new long[]{-7, -5},
            new long[]{16, -5},
            new long[]{-2, 0},
            new long[]{1, 0},
            new long[]{-4, 1},
            new long[]{1, 1},
            new long[]{-7, 2},
            new long[]{2, 2},
            new long[]{-23, 7},
            new long[]{8, 7},
            new long[]{-298, 92},
            new long[]{113, 92},
            new long[]{-1052, 325},
            new long[]{401, 325},
            new long[]{-2039, 630},
            new long[]{778, 630},
            new long[]{-7207, 2227},
            new long[]{2752, 2227},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test40() {
        System.out.println("40: x^2 - 8xy - 17y^2 + 72y - 75 = 0 (D > 0)");
        int a = 1, b = -8, c = -17, d = 0, e = 72, f = -75;

        long[][] expectedSolutions = new long[][]{
            new long[]{2098, -1199},
            new long[]{-250, -25},
            new long[]{50, -25},
            new long[]{-2, 1},
            new long[]{10, 1},
            new long[]{-34, 23},
            new long[]{218, 23},
            new long[]{-1754, 1009},
            new long[]{9826, 1009},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test41() {
        System.out.println("41: 6x^2 + 24xy + 12y^2 - x + y + 7 = 0 (D > 0)");
        int a = 6, b = 24, c = 12, d = -1, e = 1, f = 7;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, 3},
            new long[]{-27, 8},
            new long[]{-13, 22},
            new long[]{-208, 61},
            new long[]{-1908, 3257},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test42() {
        System.out.println("42: x^2 + 2xy - 7y^2 + 2x + 10y - 8 = 0 (D > 0)");
        int a = 1, b = 2, c = -7, d = 2, e = 10, f = -8;

        long[][] expectedSolutions = new long[][]{
            new long[]{-9598, -5248},
            new long[]{-3449, -1885},
            new long[]{7217, -1885},
            new long[]{-1648, -900},
            new long[]{3446, -900},
            new long[]{-593, -323},
            new long[]{1237, -323},
            new long[]{-284, -154},
            new long[]{590, -154},
            new long[]{-103, -55},
            new long[]{211, -55},
            new long[]{-50, -26},
            new long[]{100, -26},
            new long[]{-19, -9},
            new long[]{35, -9},
            new long[]{-10, -4},
            new long[]{16, -4},
            new long[]{-5, -1},
            new long[]{5, -1},
            new long[]{-4, 0},
            new long[]{2, 0},
            new long[]{-5, 1},
            new long[]{1, 1},
            new long[]{-8, 2},
            new long[]{2, 2},
            new long[]{-19, 5},
            new long[]{7, 5},
            new long[]{-38, 10},
            new long[]{16, 10},
            new long[]{-103, 27},
            new long[]{47, 27},
            new long[]{-214, 56},
            new long[]{100, 56},
            new long[]{-593, 155},
            new long[]{281, 155},
            new long[]{-1240, 324},
            new long[]{590, 324},
            new long[]{-3449, 901},
            new long[]{1645, 901},
            new long[]{-7220, 1886},
            new long[]{3446, 1886},
            new long[]{9595, 5249},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test43() {
        System.out.println("43: -9x^2 - 2xy + 5y^2 - 8x + 9y + 336 = 0 (D > 0)");
        int a = -9, b = -2, c = 5, d = -8, e = 9, f = 336;

        long[][] expectedSolutions = new long[][]{
            new long[]{-6474, -10077},
            new long[]{7910, -9149},
            new long[]{6, 3},
            new long[]{-910, 1051},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test44() {
        System.out.println("44: -3x^2 - 2xy + 9y^2 + 5x - 3y - 562 = 0 (D > 0)");
        int a = -3, b = -2, c = 9, d = 5, e = -3, f = -562;

        long[][] expectedSolutions = new long[][]{
            new long[]{-97627, -68247},
            new long[]{33298, -15877},
            new long[]{19895, -9486},
            new long[]{-10252, -7167},
            new long[]{-6125, -4282},
            new long[]{-2327, -1627},
            new long[]{2090, -996},
            new long[]{-1390, -972},
            new long[]{1249, -595},
            new long[]{475, -226},
            new long[]{284, -135},
            new long[]{-145, -102},
            new long[]{-86, -61},
            new long[]{29, -15},
            new long[]{16, -10},
            new long[]{-2, 8},
            new long[]{5, 9},
            new long[]{23, 18},
            new long[]{40, 29},
            new long[]{-77, 38},
            new long[]{-130, 63},
            new long[]{385, 269},
            new long[]{644, 450},
            new long[]{-4516, 2154},
            new long[]{-7559, 3605},
            new long[]{22166, 15495},
            new long[]{37099, 25934},
            new long[]{-71984, 34325},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test46() {
        System.out.println("46: -x^2 + 5xy - 2y^2 - 3x + 6y - 4 = 0 (D > 0)");
        int a = -1, b = 5, c = -2, d = -3, e = 6, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-21318, -48619},
            new long[]{-82420, -18068},
            new long[]{-7923, -18068},
            new long[]{-21318, -4673},
            new long[]{-2050, -4673},
            new long[]{-2050, -449},
            new long[]{-198, -449},
            new long[]{-531, -116},
            new long[]{-52, -116},
            new long[]{-198, -43},
            new long[]{-20, -43},
            new long[]{-52, -11},
            new long[]{-6, -11},
            new long[]{-20, -4},
            new long[]{-3, -4},
            new long[]{-6, -1},
            new long[]{-2, -1},
            new long[]{0, 1},
            new long[]{2, 1},
            new long[]{0, 2},
            new long[]{7, 2},
            new long[]{2, 7},
            new long[]{30, 7},
            new long[]{30, 71},
            new long[]{322, 71},
            new long[]{119, 274},
            new long[]{1248, 274},
            new long[]{322, 737},
            new long[]{3360, 737},
            new long[]{1248, 2849},
            new long[]{12994, 2849},
            new long[]{3360, 7666},
            new long[]{34967, 7666},
            new long[]{12994, 29639},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test47() {
        System.out.println("47: -9x^2 + 9xy + 21y^2 + 24x - 18y + 303 = 0 (D > 0)");
        int a = -9, b = 9, c = 21, d = 24, e = -18, f = 303;

        long[][] expectedSolutions = new long[][]{
            new long[]{80981, -73134},
            new long[]{16098, -14537},
            new long[]{-18486, -8773},
            new long[]{-16777, -7962},
            new long[]{5322, -4805},
            new long[]{1059, -955},
            new long[]{-1101, -523},
            new long[]{-334, -159},
            new long[]{98, -87},
            new long[]{21, -17},
            new long[]{-21, -10},
            new long[]{-19, -9},
            new long[]{9, -5},
            new long[]{9, 2},
            new long[]{-6, 5},
            new long[]{-19, 18},
            new long[]{71, 33},
            new long[]{351, 166},
            new long[]{-303, 275},
            new long[]{-334, 303},
            new long[]{1059, 502},
            new long[]{5322, 2525},
            new long[]{-5103, 4610},
            new long[]{-16777, 15153},
            new long[]{58301, 27666},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test48() {
        System.out.println("48: 4x^2 - 20xy - 20y^2 - 28x + 36y + 1148 = 0 (D > 0)");
        int a = 4, b = -20, c = -20, d = -28, e = 36, f = 1148;

        long[][] expectedSolutions = new long[][]{
            new long[]{9, 5},
            new long[]{23, 5},
            new long[]{-63, 77},
            new long[]{455, 77},
            new long[]{-147, 175},
            new long[]{1029, 175},
            new long[]{-3099, 3631},
            new long[]{21261, 3631},
            new long[]{-7033, 8237},
            new long[]{48225, 8237},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test49() {
        System.out.println("49: -110x^2 + 495xy + 165y^2 + 220x - 55y - 11275 = 0 (D > 0)");
        int a = -110, b = 495, c = 165, d = 220, e = -55, f = -11275;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2303, -479},
            new long[]{17, -55},
            new long[]{1, 7},
            new long[]{18705, 3887},
            new long[]{-14095, 45215},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { HyperbolicSolver.solve(a, b, c, d, e, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, HyperbolicSolver.solve(a, b, c, d, e, f));
    }
}