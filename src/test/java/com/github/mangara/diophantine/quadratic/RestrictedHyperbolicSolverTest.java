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
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class RestrictedHyperbolicSolverTest {
    
    public RestrictedHyperbolicSolverTest() {
    }

    @Test
    public void test01() {
        System.out.println("1: x^2 - 15y^2 - 61 = 0 (D > 0)");
        int a = 1, b = 0, c = -15, d = 0, e = 0, f = -61;

        long[][] expectedSolutions = new long[][]{
            new long[]{-6251, -1614},
            new long[]{6251, -1614},
            new long[]{-4574, -1181},
            new long[]{4574, -1181},
            new long[]{-794, -205},
            new long[]{794, -205},
            new long[]{-581, -150},
            new long[]{581, -150},
            new long[]{-101, -26},
            new long[]{101, -26},
            new long[]{-74, -19},
            new long[]{74, -19},
            new long[]{-14, -3},
            new long[]{14, -3},
            new long[]{-11, -2},
            new long[]{11, -2},
            new long[]{-11, 2},
            new long[]{11, 2},
            new long[]{-14, 3},
            new long[]{14, 3},
            new long[]{-74, 19},
            new long[]{74, 19},
            new long[]{-101, 26},
            new long[]{101, 26},
            new long[]{-581, 150},
            new long[]{581, 150},
            new long[]{-794, 205},
            new long[]{794, 205},
            new long[]{-4574, 1181},
            new long[]{4574, 1181},
            new long[]{-6251, 1614},
            new long[]{6251, 1614},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { RestrictedHyperbolicSolver.solve(a, b, c, f); });
        //TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative01() {
        System.out.println("Representative 1: x^2 - 15y^2 - 61 = 0 (D > 0)");
        int a = 1, b = 0, c = -15, d = 0, e = 0, f = -61;

        long[][] expectedSolutions = new long[][]{
            new long[]{-11, 2},
            new long[]{11, 2},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertNotSupportedYet(() -> { RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)); });
//        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }

    private void validateRepresentativeSolutions(int a, int b, int c, int d, int e, int f, long[][] expectedSolutions) {
        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        
        for (long[] sol : expectedSolutions) {
            for (long[] sol2 : expectedSolutions) {
                if (sol[0] == sol2[0] && sol[1] == sol2[1]) {
                    continue; // No need to check against itself
                }
                
                if (sameClass(a, b, c, f, sol[0], sol[1], sol2[0], sol2[1])) {
                    fail(String.format("(%d, %d) and (%d, %d) belong to the same class.", sol[0], sol[1], sol2[0], sol2[1]));
                }
            }
        }
    }
    
    private void assertRepresentativeSolutions(int a, int b, int c, int d, int e, int f, long[][] expectedSolutions, List<XYPair> representativeSolutions) {
        if (representativeSolutions.size() != expectedSolutions.length) {
            if (representativeSolutions.size() > expectedSolutions.length) {
                fail(String.format("Too many solutions returned; expected %s but got %s.", Arrays.deepToString(expectedSolutions), representativeSolutions.toString()));
            } else {
                fail(String.format("Too few solutions returned; expected %s but got %s.", Arrays.deepToString(expectedSolutions), representativeSolutions.toString()));
            }
        }
        
        boolean[] seen = new boolean[expectedSolutions.length];
        int numSeen = 0;
        
        for (XYPair sol : representativeSolutions) {
            TestUtils.assertIsSolution(a, b, c, 0, 0, f, sol);

            long x = sol.x.longValueExact();
            long y = sol.y.longValueExact();

            boolean found = false;

            for (int i = 0; i < expectedSolutions.length; i++) {
                if (seen[i]) {
                    continue;
                }
                if (sameClass(a, b, c, f, x, y, expectedSolutions[i][0], expectedSolutions[i][1])) {
                    seen[i] = true;
                    numSeen++;
                    found = true;
                    break;
                }
            }

            if (!found) {
                fail(String.format("Unexpected solution (%d, %d) returned; expected %s but got %s.", x, y, Arrays.deepToString(expectedSolutions), representativeSolutions.toString()));
            }
        }
    }
    
    private boolean sameClass(long a, long b, long c, long f, long x1, long y1, long x2, long y2) {
        long cong1 = 2 * a * x1 * x2 + b * (x1 * y2 + x2 * y1) + 2 * c * y1 * y2;
        long cong2 = x1 * y2 - x2 * y1;

        return cong1 % Math.abs(f) == 0 && cong2 % Math.abs(f) == 0;
    }

    
}
