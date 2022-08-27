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
import com.github.mangara.diophantine.XYPair;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
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
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //TestUtils.assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
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
        //assertAllSolutions(a, b, c, d, e, f, expectedSolutions, EllipticalSolver.solve(a, b, c, d, e, f));
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
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
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
        TestUtils.assertNotSupportedYet(() -> { EllipticalSolver.solve(a, b, c, d, e, f); });
        //assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
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
        //assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
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
        //assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions);
    }
    
}
