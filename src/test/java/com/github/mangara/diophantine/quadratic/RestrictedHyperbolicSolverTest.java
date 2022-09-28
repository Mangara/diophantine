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

public class RestrictedHyperbolicSolverTest {
    
    public RestrictedHyperbolicSolverTest() {
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
        TestUtils.assertNotSupportedYet(() -> { RestrictedHyperbolicSolver.solve(a, b, c, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
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
        TestUtils.assertNotSupportedYet(() -> { RestrictedHyperbolicSolver.solve(a, b, c, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
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
        TestUtils.assertNotSupportedYet(() -> { RestrictedHyperbolicSolver.solve(a, b, c, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
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
        TestUtils.assertNotSupportedYet(() -> { RestrictedHyperbolicSolver.solve(a, b, c, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
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
        TestUtils.assertNotSupportedYet(() -> { RestrictedHyperbolicSolver.solve(a, b, c, f); });
//        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }
    
}
