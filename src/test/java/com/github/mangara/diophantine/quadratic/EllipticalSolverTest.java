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

public class EllipticalSolverTest {
    
    public EllipticalSolverTest() {
    }

    @Test
    public void test02() {
        System.out.println("2: 3x^2 - 8xy + 7y^2 - 4x + 2y - 109 = 0 (D < 0)");
        int a = 3, b = -8, c = 7, d = -4, e = 2, f = -109;

        long[][] expectedSolutions = new long[][]{
            new long[]{2, 5},
            new long[]{2, -3},
            new long[]{14, 9},
            new long[]{-10, -7},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test09() {
        System.out.println("9: 81x^2 + 78xy + 22y^2 - 225 = 0 (D < 0)");
        int a = 81, b = 78, c = 22, d = 0, e = 0, f = -225;

        long[][] expectedSolutions = new long[][]{
            new long[]{-3, 3},
            new long[]{3, -3},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test10() {
        System.out.println("10: 2x^2 + 3xy + 5y^2 - 3352 = 0 (D < 0)");
        int a = 2, b = 3, c = 5, d = 0, e = 0, f = -3352;

        long[][] expectedSolutions = new long[][]{
            new long[]{-46, 10},
            new long[]{-31, -10},
            new long[]{-17, -19},
            new long[]{17, 19},
            new long[]{31, 10},
            new long[]{46, -10},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test19() {
        System.out.println("19: 42x^2 + 8xy + 15y^2 + 23x + 17y - 4915 = 0 (D < 0)");
        int a = 42, b = 8, c = 15, d = 23, e = 17, f = -4915;

        long[][] expectedSolutions = new long[][]{
            new long[]{-11, -1},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test30() {
        System.out.println("30: -3x^2 - 5y^2 + 437 = 0 (D < 0)");
        int a = -3, b = 0, c = -5, d = 0, e = 0, f = 437;

        long[][] expectedSolutions = new long[][]{
            new long[]{-12, -1},
            new long[]{-12, 1},
            new long[]{-8, -7},
            new long[]{-8, 7},
            new long[]{8, -7},
            new long[]{8, 7},
            new long[]{12, -1},
            new long[]{12, 1},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test33() {
        System.out.println("33: -5x^2 + 6xy - 2y^2 + 40 = 0 (D < 0)");
        int a = -5, b = 6, c = -2, d = 0, e = 0, f = 40;

        long[][] expectedSolutions = new long[][]{
            new long[]{-8, -14},
            new long[]{-8, -10},
            new long[]{-4, -10},
            new long[]{-4, -2},
            new long[]{4, 2},
            new long[]{4, 10},
            new long[]{8, 10},
            new long[]{8, 14},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test36() {
        System.out.println("36: -6x^2 + 7xy - 8y^2 - 9x - 7y + 60 = 0 (D < 0)");
        int a = -6, b = 7, c = -8, d = -9, e = -7, f = 60;

        long[][] expectedSolutions = new long[][]{
            new long[]{-4, 0},
            new long[]{2, 2},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test38() {
        System.out.println("38: -4x^2 + 3xy - y^2 - 3x + 2y + 78 = 0 (D < 0)");
        int a = -4, b = 3, c = -1, d = -3, e = 2, f = 78;

        long[][] expectedSolutions = new long[][]{
            new long[]{-6, -12},
            new long[]{-6, -4},
            new long[]{6, 6},
            new long[]{6, 14},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test57() {
        System.out.println("57: 7x^2 - 9xy + 3y^2 - 19 = 0 (D < 0)");
        int a = 7, b = -9, c = 3, d = 0, e = 0, f = -19;

        long[][] expectedSolutions = new long[][]{
            new long[]{-8, -13},
            new long[]{-7, -12},
            new long[]{-8, -11},
            new long[]{-7, -9},
            new long[]{-1, -4},
            new long[]{1, -1},
            new long[]{-1, 1},
            new long[]{1, 4},
            new long[]{7, 9},
            new long[]{8, 11},
            new long[]{7, 12},
            new long[]{8, 13},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test58() {
        System.out.println("58: 7x^2 + 5xy + y^2 - 36 = 0 (D < 0)");
        int a = 7, b = 5, c = 1, d = 0, e = 0, f = -36;

        long[][] expectedSolutions = new long[][]{
            new long[]{6, -18},
            new long[]{6, -12},
            new long[]{0, -6},
            new long[]{0, 6},
            new long[]{-6, 12},
            new long[]{-6, 18},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test59() {
        System.out.println("59: x^2 - xy + y^2 - 8 = 0 (D < 0)");
        int a = 1, b = -1, c = 1, d = 0, e = 0, f = -8;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test60() {
        System.out.println("60: x^2 - 4xy + 5y^2 - 5 = 0 (D < 0)");
        int a = 1, b = -4, c = 5, d = 0, e = 0, f = -5;

        long[][] expectedSolutions = new long[][]{
            new long[]{-5, -2},
            new long[]{-3, -2},
            new long[]{-4, -1},
            new long[]{0, -1},
            new long[]{0, 1},
            new long[]{4, 1},
            new long[]{3, 2},
            new long[]{5, 2},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test61() {
        System.out.println("61: 5x^2 - 4xy + y^2 - 9 = 0 (D < 0)");
        int a = 5, b = -4, c = 1, d = 0, e = 0, f = -9;

        long[][] expectedSolutions = new long[][]{
            new long[]{-3, -6},
            new long[]{0, -3},
            new long[]{0, 3},
            new long[]{3, 6},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test62() {
        System.out.println("62: x^2 + y^2 - 7 = 0 (D < 0)");
        int a = 1, b = 0, c = 1, d = 0, e = 0, f = -7;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test63() {
        System.out.println("63: 7x^2 + 4xy + y^2 - 36 = 0 (D < 0)");
        int a = 7, b = 4, c = 1, d = 0, e = 0, f = -36;

        long[][] expectedSolutions = new long[][]{
            new long[]{3, -9},
            new long[]{0, -6},
            new long[]{3, -3},
            new long[]{-3, 3},
            new long[]{0, 6},
            new long[]{-3, 9},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test64() {
        System.out.println("64: 8x^2 + 3xy + 5y^2 - 5 = 0 (D < 0)");
        int a = 8, b = 3, c = 5, d = 0, e = 0, f = -5;

        long[][] expectedSolutions = new long[][]{
            new long[]{0, -1},
            new long[]{0, 1},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }

    @Test
    public void test65() {
        System.out.println("65: 3x^2 + xy + 8y^2 - 4 = 0 (D < 0)");
        int a = 3, b = 1, c = 8, d = 0, e = 0, f = -4;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, EllipticalSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test66() {
        System.out.println("66: x^2 + 3xy + 3y^2 - 4 = 0 (D < 0)");
        int a = 1, b = 3, c = 3, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{2, -2},
            new long[]{4, -2},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-4, 2},
            new long[]{-2, 2},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test67() {
        System.out.println("67: 5x^2 + 4xy + y^2 - 2 = 0 (D < 0)");
        int a = 5, b = 4, c = 1, d = 0, e = 0, f = -2;

        long[][] expectedSolutions = new long[][]{
            new long[]{1, -3},
            new long[]{1, -1},
            new long[]{-1, 1},
            new long[]{-1, 3},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
    }
    
    @Test
    public void test68() {
        System.out.println("68: -3x^2 - 4xy - 6y^2 - 2 = 0 (D < 0)");
        int a = -3, b = -4, c = -6, d = 0, e = 0, f = -2;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, EllipticalSolver.solve(a, b, c, d, e, f));
    }
    
}
