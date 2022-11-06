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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.function.Executable;

public class TestUtils {

    public static void assertIsSolution(int a, int b, int c, int d, int e, int f, long x, long y) {
        assertIsSolution(a, b, c, d, e, f, new XYPair(x, y));
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
                String.format("(%d, %d) is not a solution to %s", sol.x, sol.y, prettyPrintEquation(a, b, c, d, e, f)));
    }

    public static void validateExpectedSolutions(int a, int b, int c, int d, int e, int f, long[][] expectedSolutions) {
        for (long[] expectedSolution : expectedSolutions) {
            assertIsSolution(a, b, c, d, e, f, expectedSolution[0], expectedSolution[1]);
        }
    }
    
    public static void validateExpectedSolutions(int a, int b, int c, int d, int e, int f, List<XYPair> expectedSolutions) {
        for (XYPair expectedSolution : expectedSolutions) {
            assertIsSolution(a, b, c, d, e, f, expectedSolution);
        }
    }

    public static void assertSolutionsInclude(int a, int b, int c, int d, int e, int f, long[][] expectedSolutions, Iterator<XYPair> solutions) {
        List<XYPair> expected = new ArrayList<>(expectedSolutions.length);
        
        for (long[] expectedSolution : expectedSolutions) {
            expected.add(new XYPair(expectedSolution[0], expectedSolution[1]));
        }
        
        assertSolutionsInclude(a, b, c, d, e, f, expected, solutions);
    }
    
    public static void assertSolutionsInclude(int a, int b, int c, int d, int e, int f, List<XYPair> expectedSolutions, Iterator<XYPair> solutions) {
        boolean[] seen = new boolean[expectedSolutions.size()];
        int numSeen = 0;

        for (int n = 0; n < 1000 && solutions.hasNext() && numSeen < expectedSolutions.size(); n++) {
            XYPair sol = solutions.next();
            assertIsSolution(a, b, c, d, e, f, sol);

            for (int i = 0; i < expectedSolutions.size(); i++) {
                if (!seen[i] && sol.equals(expectedSolutions.get(i))) {
                    seen[i] = true;
                    numSeen++;
                    break;
                }
            }
        }

        if (numSeen < expectedSolutions.size()) {
            fail("Not all solutions found. Seen: " + seenString(expectedSolutions, seen, true) + " Not seen: " + seenString(expectedSolutions, seen, false));
        }
    }

    public static void assertAllSolutions(int a, int b, int c, int d, int e, int f, long[][] expectedSolutions, Iterator<XYPair> solutions) {
        
    }
    
    public static void assertAllSolutions(int a, int b, int c, int d, int e, int f, List<XYPair> expectedSolutions, Iterator<XYPair> solutions) {
        boolean[] seen = new boolean[expectedSolutions.size()];
        int numSeen = 0;

        for (int n = 0; n < 1000 && solutions.hasNext() && numSeen < expectedSolutions.size(); n++) {
            XYPair sol = solutions.next();
            assertIsSolution(a, b, c, d, e, f, sol);

            boolean found = false;
            
            for (int i = 0; i < expectedSolutions.size(); i++) {
                if (!seen[i] && sol.equals(expectedSolutions.get(i))) {
                    seen[i] = true;
                    numSeen++;
                    found = true;
                    break;
                }
            }

            if (!found) {
                fail("Unexpected solution returned: " + sol);
            }
        }

        if (numSeen < expectedSolutions.size()) {
            fail("Not all solutions found. Seen: " + seenString(expectedSolutions, seen, true) + " Not seen: " + seenString(expectedSolutions, seen, false));
        }
    }

    public static void assertNoSolutions(int a, int b, int c, int d, int e, int f, Iterator<XYPair> solutions) {
        if (solutions.hasNext()) {
            XYPair sol = solutions.next();
            assertIsSolution(a, b, c, d, e, f, sol);
            fail("Expected no solutions, but found at least one solution: " + sol);
        }
    }

    public static void assertNotSupportedYet(Executable code) {
        assertThrows(UnsupportedOperationException.class, code);
    }

    private static String seenString(List<XYPair> expectedSolutions, boolean[] seen, boolean found) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < expectedSolutions.size(); i++) {
            if (seen[i] == found) {
                sb.append(String.format("(%d, %d), ", expectedSolutions.get(i).x, expectedSolutions.get(i).y));
            }
        }

        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length()); // Remove final ", "
        }

        return sb.toString();
    }
    
    public static String prettyPrintEquation(int a, int b, int c, int d, int e, int f) {
        StringBuilder sb = new StringBuilder();
        
        appendTerm(sb, a, "x^2");
        appendTerm(sb, b, "xy");
        appendTerm(sb, c, "y^2");
        appendTerm(sb, d, "x");
        appendTerm(sb, e, "y");
        appendTerm(sb, f, "");
        sb.append(" = 0");
        
        long D = Utils.discriminant(a, b, c);
        String discriminantComparator = D > 0 ? ">" : (D < 0 ? "<" : "=");
        sb.append(" (D ").append(discriminantComparator).append(" 0)");
        
        return sb.toString();
    }
    
    private static void appendTerm(StringBuilder sb, int factor, String variables) {
        if (sb.isEmpty() && factor == 1) {
            sb.append(variables);
        } else if (sb.isEmpty() && factor == -1) {
            sb.append("-").append(variables);
        } else if (sb.isEmpty() && factor != 0) {
            sb.append(Integer.toString(factor)).append(variables);
        } else if (factor == 1 && !variables.isEmpty()) {
            sb.append(" + ").append(variables);
        } else if (factor > 0) {
            sb.append(" + ").append(Integer.toString(factor)).append(variables);
        } else if (factor == -1 && !variables.isEmpty()) {
            sb.append(" - ").append(variables);
        } else if (factor < 0) {
            sb.append(" - ").append(Integer.toString(Math.abs(factor))).append(variables);
        }
    }
}
