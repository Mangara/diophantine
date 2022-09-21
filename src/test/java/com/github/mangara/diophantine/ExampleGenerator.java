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

import com.github.mangara.diophantine.quadratic.RestrictedEllipticalSolver;
import com.github.mangara.diophantine.quadratic.UnaryCongruenceSolver;
import com.github.mangara.diophantine.utils.Divisors;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExampleGenerator {

    public static void main(String[] args) {
//        findParabolicExamples();
//        findSquareDiscriminantExamples();
//        findRestrictedEllipticalExamples();
        generate();
    }
        
    private static void generate() {
        // Pick a quadratic equation a x^2 + b xy + c y^2 + d x + e y + f = 0
//        int a = 0;//smallRandomNumber();
//        int b = 0;//smallRandomNumber();
//        int c = smallRandomNumber();
//        int d = smallRandomNumber();
//        int e = smallRandomNumber();
//        int f = ensureSmallPositiveSolution(a, b, c, d, e);
        int a = 3, b = -8, c = 7, d = -4, e = 2, f = -109;
        
        String solver = "RestrictedEllipticalSolver.solve(a, b, c, f)";
        
        long D = Utils.discriminant(a, b, c);

        System.out.println("Equation: " + TestUtils.prettyPrintEquation(a, b, c, d, e, f));
        System.out.println("D = " + D + " GCD(a, b, c) = " + Utils.gcd(a, b, c) + " GCD(a, f) = " + Utils.gcd(a, f) + " GCD(d, e) = " + Utils.gcd(d, e));

        List<XYPair> solutions = bruteForceSmallSolutions(a, b, c, d, e, f, 10000, true);

        if (D < 0) {
            printTestCase("all", solver, a, b, c, d, e, f, solutions);
        } else {
            printTestCase("", solver, a, b, c, d, e, f, solutions);
        }

        if (d == 0 && e == 0 && D > 0) {
            List<XYPair> representativeSolutions = keepRepresentativeSolutions(solutions, a, b, c, d, e, f);
            printTestCase("Representative", solver, a, b, c, d, e, f, representativeSolutions);

            List<XYPair> primitiveSolutions = keepPrimitiveSolutions(representativeSolutions);
            printTestCase("Primitive", solver, a, b, c, d, e, f, primitiveSolutions);
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

    private static List<XYPair> bruteForceSmallSolutions(int a, int b, int c, int d, int e, int f, int bound, boolean verbose) {
        List<XYPair> solutions = new ArrayList<>();

        if (verbose) {
            System.out.println();
            System.out.println("Small integer solutions:");
        }
        
        for (long y = -bound; y <= bound; y++) {
            for (long x = -bound; x <= bound; x++) {
                if (a * x * x + b * x * y + c * y * y + d * x + e * y + f == 0) {
                    if (verbose) {
                        System.out.printf("(%d, %d)%n", x, y);
                    }
                    
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

    private static void printTestCase(String type, String solver, int a, int b, int c, int d, int e, int f, List<XYPair> solutions) {
        System.out.println();
        String testName = type == "all" ? "" : type;
        if (solutions.isEmpty()) {
            System.out.printf(
                      "    @Test\n"
                    + "    public void test%sN() {\n"
                    + "        System.out.println(\"N: %s\");\n"
                    + "        int a = %d, b = %d, c = %d, d = %d, e = %d, f = %d;\n"
                    + "\n"
                    + "        TestUtils.assertNotSupportedYet(() -> { %s; });\n"
                    + "        //TestUtils.assertNoSolutions(a, b, c, d, e, f, %s);\n"
                    + "    }\n", testName, TestUtils.prettyPrintEquation(a, b, c, d, e, f), a, b, c, d, e, f, solver, solver);
        } else {
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
                case "all" -> {
                    unsupportedMethod = "TestUtils.assertNotSupportedYet";
                    testMethod = "TestUtils.assertAllSolutions";
                }
                default -> {
                    unsupportedMethod = "TestUtils.assertNotSupportedYet";
                    testMethod = "TestUtils.assertSolutionsInclude";
                }
            }

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
                    + "    }\n", testName, TestUtils.prettyPrintEquation(a, b, c, d, e, f), a, b, c, d, e, f, unsupportedMethod, solver, testMethod, solver);
        }
    }

    private static Optional<XYPair> leastPositiveSolution(List<XYPair> solutions) {
        return solutions.stream()
                .filter((sol) -> sol.x.signum() > 0 && sol.y.signum() > 0)
                .min((sol1, sol2) -> sol1.x.compareTo(sol2.x));
    }
    
    private static void findParabolicExamples() {
        for (int a = 1; a < 10; a++) {
            for (int b = 0; b < 10; b += 2) {
                // Test D = b^2 - 4ac = 0
                if ((b * b) % (4 * a) != 0) {
                    continue;
                }
                int c = (b * b) / (4 * a);
                
                for (int d = 0; d < 10; d++) {
                    // Ensure u = bd - 2ae != 0
                    int avoidE = -1;
                    if ((b * d) % ( 2 * a) != 0) {
                        avoidE = (b * d) / (2 * a);
                    }
                    
                    for (int e = 0; e < 10; e++) {
                        if (e == avoidE) {
                            continue;
                        }
                        
                        for (int f = 0; f < 10; f++) {
                            int u = b * d - 2 * a * e;
                            int v = d * d - 4 * a * f;
                            
                            if (Math.abs(u) <= 1) {
                                continue;
                            }

                            List<BigInteger> Ti = UnaryCongruenceSolver.solveReduced(1, -v, Math.abs(u));
                            
                            if (Ti.isEmpty()) {
                                System.out.printf("int a = %d, b = %d, c = %d, d = %d, e = %d, f = %d;%n", a, b, c, d, e, f);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void findSquareDiscriminantExamples() {
        int bound = 9;
        for (int g = 1; g <= bound; g++) {
            for (int a = -bound; a <= bound; a++) {
                if (a == 0) {
                    continue; // Looking for a != 0 currently
                }
                
                for (int b = -bound; b <= bound; b++) {
                    if (b == g || b == -g) {
                        continue; // Looking for |b| != |g| currently
                    }
                    
                    if (a == 0) {
                        if (b != g) {
                            continue;
                        }
                        
                        for (int c = -bound; c <= bound; c++) {
                            findSquareDiscriminantExamples2(a, b, c);
                        }
                    } else {
                        int top = b * b - g * g;
                        if (top % (4 * a) != 0) {
                            continue;
                        }

                        int c = top / (4 * a);
                    
                        findSquareDiscriminantExamples2(a, b, c);
                    }
                }
            }
        }
    }
    
    private static void findSquareDiscriminantExamples2(int a, int b, int c) {
        long D = Utils.discriminant(a, b, c);
        long g = Math.round(Math.sqrt(D));
        
        long g1 = Utils.gcd(Math.multiplyExact(2L, a), Math.addExact(b, g));
        long g2 = Utils.gcd(Math.multiplyExact(2L, a), Math.subtractExact(b, g));
        long g1g2 = Math.multiplyExact(g1, g2);
        
        int bound = 9;
        
        for (int d = -bound; d <= bound; d++) {
            for (int e = -bound; e <= bound; e++) {
                long alpha = Utils.legendreAlpha(b, c, d, e);
                long beta = Utils.legendreBeta(a, b, d, e);

                long Dg1 = Math.multiplyExact(D, g1);
                long right1 = Math.addExact(
                        Math.multiplyExact(2L * a, alpha),
                        Math.multiplyExact(Math.addExact(b, g), beta));
                
                if (right1 % Dg1 != 0) {
                    continue;
                }
                
                long Dg2 = Math.multiplyExact(D, g2);
                long right2 = Math.addExact(
                        Math.multiplyExact(2L * a, alpha),
                        Math.multiplyExact(Math.subtractExact(b, g), beta));

                if (right2 % Dg2 != 0) {
                    continue;
                }
                
                for (int f = -bound; f <= bound; f++) {
                    long k = Utils.legendreConstant(a, b, c, d, e, f, D);
                    
                    if (k == 0) {
                        System.out.printf("int a = %d, b = %d, c = %d, d = %d, e = %d, f = %d;%n", a, b, c, d, e, f);
                    }
                }
            }
        }
    }

    private static void findRestrictedEllipticalExamples() {
        int bound = 9;
        for (int a = 1; a <= bound; a++) {
            for (int b = -bound; b < bound; b++) {
                for (int c = -bound; c < bound; c++) {
                    int D = b * b - 4 * a * c;
                    
                    if (D >= 0) {
                        continue;
                    }
                    
                    if (D != -4) {
                        continue;
                    }
                    
                    for (int f = -bound; f < 0; f++) {
                        if (Utils.gcd(a, f) != 1) {
                            continue;
                        }
//                        int numSmallSolutions = bruteForceSmallSolutions(a, b, c, 0, 0, f, bound, false).size();
//
//                        if (numSmallSolutions == 0) {
//                            System.out.printf("int a = %d, b = %d, c = %d, f = %d;  (D = %d, %d square divisors, %d small solutions)%n", a, b, c, f, D, Divisors.getSquareDivisors(Math.abs(f)).size(), numSmallSolutions);
//                        }
                        try {
                            RestrictedEllipticalSolver.solve(a, b, c, f);
                        } catch (RuntimeException ex) {
                            if (ex.getMessage().equals("Boom")) {
                                System.out.printf("int a = %d, b = %d, c = %d, f = %d;  (D = %d, %d square divisors)%n", a, b, c, f, D, Divisors.getSquareDivisors(Math.abs(f)).size());
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean hasSmallSolutions(int a, int b, int c, int d, int e, int f, int bound) {
        return !bruteForceSmallSolutions(a, b, c, d, e, f, bound, false).isEmpty();
    }
}
