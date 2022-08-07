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
package com.github.mangara.diophantine;

import java.math.BigInteger;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    public static void assertIsSolution(int a, int b, int c, int d, int e, int f, long x, long y) {
        assertIsSolution(a, b, c, d, e, f, new XYPair(BigInteger.valueOf(x), BigInteger.valueOf(y)));
    }
    
    public static void assertIsSolution(int a, int b, int c, int d, int e, int f, XYPair sol) {
        BigInteger result = BigInteger.valueOf(a).multiply(sol.x).multiply(sol.x).add(
                BigInteger.valueOf(b).multiply(sol.x).multiply(sol.y)
        ).add(
                BigInteger.valueOf(c).multiply(sol.y).multiply(sol.y)
        ).add(
                BigInteger.valueOf(d).multiply(sol.x)
        ).add(
                BigInteger.valueOf(e).multiply(sol.y)
        ).add(
                BigInteger.valueOf(f)
        );

        assertEquals(BigInteger.ZERO, result,
                String.format("(%d, %d) is not a solution to %dx^2 + %dxy + %dy^2 + %dx + %dy + %d = 0", sol.x, sol.y, a, b, c, d, e, f));
    }

    public static void validateExpectedSolutions(int a, int b, int c, int d, int e, int f, long[][] expectedSolutions) {
        for (long[] expectedSolution : expectedSolutions) {
            assertIsSolution(a, b, c, d, e, f, expectedSolution[0], expectedSolution[1]);
        }
    }

    public static void assertSolutionsInclude(int a, int b, int c, int d, int e, int f, long[][] expectedSolutions, Iterator<XYPair> solutions) {
        boolean[] seen = new boolean[expectedSolutions.length];
        int numSeen = 0;

        for (int n = 0; n < 1000 && solutions.hasNext() && numSeen < expectedSolutions.length; n++) {
            XYPair sol = solutions.next();
            assertIsSolution(a, b, c, d, e, f, sol);

            try {
                long x = sol.x.longValueExact();
                long y = sol.y.longValueExact();

                for (int i = 0; i < expectedSolutions.length; i++) {
                    if (!seen[i] && x == expectedSolutions[i][0] && y == expectedSolutions[i][1]) {
                        seen[i] = true;
                        numSeen++;
                        break;
                    }
                }
            } catch (ArithmeticException ex) {
                // BigInteger out of long range - skip this solution
            }
        }
        
        assertEquals(expectedSolutions.length, numSeen, "Not all solutions found. Seen: " + seenString(expectedSolutions, seen, true) + " Not seen: " + seenString(expectedSolutions, seen, false));
    }

    private static String seenString(long[][] expectedSolutions, boolean[] seen, boolean found) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < expectedSolutions.length; i++) {
            if (seen[i] == found) {
                sb.append(String.format("(%d, %d), ", expectedSolutions[i][0], expectedSolutions[i][1]));
            }
        }
        
        sb.delete(sb.length() - 2, sb.length()); // Remove final ", "
        
        return sb.toString();
    }
}
