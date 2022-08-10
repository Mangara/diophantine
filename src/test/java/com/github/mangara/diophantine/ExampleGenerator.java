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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExampleGenerator {

    public static void main(String[] args) {
        generate();
    }
        
    private static void generate() {
        // Pick a quadratic equation a x^2 + b xy + c y^2 + d x + e y + f = 0
        int a = 0;//smallRandomNumber();
        int b = 0;//smallRandomNumber();
        int c = smallRandomNumber();
        int d = smallRandomNumber();
        int e = smallRandomNumber();
        int f = ensureSmallPositiveSolution(a, b, c, d, e);
//        int a = -110, b = 495, c = 165, d = 220, e = -55, f = -11275;

        System.out.println("Equation: " + prettyPrintEquation(a, b, c, d, e, f));
        System.out.println("GCD(a, b, c) = " + Utils.gcd(a, b, c));
        System.out.println("GCD(a, f) = " + Utils.gcd(a, f));
        System.out.println("GCD(d, e) = " + Utils.gcd(d, e));

        List<XYPair> solutions = bruteForceSmallSolutions(a, b, c, d, e, f);

        printTestCase("", a, b, c, d, e, f, solutions);

        if (d == 0 && e == 0) {
            List<XYPair> representativeSolutions = keepRepresentativeSolutions(solutions, a, b, c, d, e, f);
            printTestCase("Representative", a, b, c, d, e, f, representativeSolutions);

            List<XYPair> primitiveSolutions = keepPrimitiveSolutions(representativeSolutions);
            printTestCase("Primitive", a, b, c, d, e, f, primitiveSolutions);
        }

        if (a == 1 && b == 0 && c < 0 && d == 0 && e == 0) {
            System.out.println();
            System.out.println("Least positive solution: " + leastPositiveSolution(solutions));
        }
    }

    private static int smallRandomNumber() {
        // Random integer between 1 and 9 (inclusive)
        int n = 1 + (int) (Math.random() * 9);
        return (Math.random() < 0.5 ? n : -n);
    }

    private static int ensureSmallPositiveSolution(int a, int b, int c, int d, int e) {
        // Pick f such that the equation has a small positive solution
        int x = Math.abs(smallRandomNumber());
        int y = Math.abs(smallRandomNumber());

        System.out.printf("Picked f to ensure that (%d, %d) is a solution.%n", x, y);

        return -1 * (a * x * x + b * x * y + c * y * y + d * x + e * y);
    }

    private static List<XYPair> bruteForceSmallSolutions(int a, int b, int c, int d, int e, int f) {
        int bound = 1000;
        List<XYPair> solutions = new ArrayList<>();

        System.out.println();
        System.out.println("Small integer solutions:");
        for (long y = -bound; y <= bound; y++) {
            for (long x = -bound; x <= bound; x++) {
                if (a * x * x + b * x * y + c * y * y + d * x + e * y + f == 0) {
                    System.out.printf("(%d, %d)%n", x, y);
                    solutions.add(new XYPair(x, y));
                }
            }
        }

        return solutions;
    }

    private static List<XYPair> keepRepresentativeSolutions(List<XYPair> solutions, int a, int b, int c, int d, int e, int f) {
        List<XYPair> representative = new ArrayList<>();

        for (int i = 0; i < solutions.size(); i++) {
            XYPair candidate = solutions.get(i);

            if (candidate.y.signum() < 0) {
                continue;
            }

            boolean valid = true;

            for (int j = 0; j < representative.size(); j++) {
                XYPair sol = representative.get(j);

                if (sameClass(a, b, c, f, candidate.x.longValueExact(), candidate.y.longValueExact(), sol.x.longValueExact(), sol.y.longValueExact())) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                representative.add(candidate);
            }
        }

        return representative;
    }

    private static List<XYPair> keepPrimitiveSolutions(List<XYPair> solutions) {
        return solutions.stream()
                .filter(sol -> sol.x.gcd(sol.y) == BigInteger.ONE)
                .collect(Collectors.toList());
    }

    private static boolean sameClass(long a, long b, long c, long f, long x1, long y1, long x2, long y2) {
        long cong1 = 2 * a * x1 * x2 + b * (x1 * y2 + x2 * y1) + 2 * c * y1 * y2;
        long cong2 = x1 * y2 - x2 * y1;

        return cong1 % Math.abs(f) == 0 && cong2 % Math.abs(f) == 0;
    }

    private static void printTestCase(String type, int a, int b, int c, int d, int e, int f, List<XYPair> solutions) {
        String solutionList = solutions.stream()
                .map((sol) -> String.format("            new long[]{%d, %d},\n", sol.x, sol.y))
                .collect(Collectors.joining());

        String unsupportedMethod, testMethod;

        switch (type) {
            case "Primitive" -> {
                unsupportedMethod = "assertPrimitiveNotSupportedYet";
                testMethod = "assertPrimitiveSolutions";
            }
            case "Representative" -> {
                unsupportedMethod = "assertRepresentativeNotSupportedYet";
                testMethod = "assertRepresentativeSolutions";
            }
            default -> {
                unsupportedMethod = "TestUtils.assertNotSupportedYet";
                testMethod = "TestUtils.assertSolutionsInclude";
            }
        }
        
        String solver = "ParabolicSolver.solve(a, b, c, d, e, f)";

        System.out.println();
        System.out.printf(
                  "    @Test\n"
                + "    public void test%sN() {\n"
                + "        System.out.println(\"N: %s\");\n"
                + "        int a = %d, b = %d, c = %d, d = %d, e = %d, f = %d;\n"
                + "\n"
                + "        long[][] expectedSolutions = new long[][]{\n"
                + solutionList
                + "        };\n"
                + "\n"
                + "        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);\n"
                + "        %s(() -> { %s; });\n"
                + "        //%s(a, b, c, d, e, f, expectedSolutions, %s);\n"
                + "    }\n", type, prettyPrintEquation(a, b, c, d, e, f), a, b, c, d, e, f, unsupportedMethod, solver, testMethod, solver);
    }

    private static Optional<XYPair> leastPositiveSolution(List<XYPair> solutions) {
        return solutions.stream()
                .filter((sol) -> sol.x.signum() > 0 && sol.y.signum() > 0)
                .min((sol1, sol2) -> sol1.x.compareTo(sol2.x));
    }

    private static String prettyPrintEquation(int a, int b, int c, int d, int e, int f) {
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
        if (sb.isEmpty() && factor != 0) {
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
