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
package io.github.mangara.diophantine.quadratic;

import io.github.mangara.diophantine.TestUtils;
import io.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
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
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test11() {
        System.out.println("11: 3x^2 - 22xy + 25y^2 - 81 = 0 (D > 0)");
        int a = 3, b = -22, c = 25, d = 0, e = 0, f = -81;

        long[][] expectedSolutions = new long[][]{
            new long[]{-9882, -7029},
            new long[]{-3432, -579},
            new long[]{-814, -579},
            new long[]{-658, -111},
            new long[]{-156, -111},
            new long[]{-54, -9},
            new long[]{-12, -9},
            new long[]{2, -1},
            new long[]{-2, 1},
            new long[]{12, 9},
            new long[]{54, 9},
            new long[]{156, 111},
            new long[]{658, 111},
            new long[]{814, 579},
            new long[]{3432, 579},
            new long[]{9882, 7029},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative11() {
        System.out.println("Representative 11: 3x^2 - 22xy + 25y^2 - 81 = 0 (D > 0)");
        int a = 3, b = -22, c = 25, d = 0, e = 0, f = -81;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, 1},
            new long[]{12, 9},
            new long[]{54, 9},
            new long[]{156, 111},
            new long[]{658, 111},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test22() {
        System.out.println("22: 2x^2 + 5xy + y^2 + 1 = 0 (D > 0)");
        int a = 2, b = 5, c = 1, d = 0, e = 0, f = 1;

        long[][] expectedSolutions = new long[][]{
            new long[]{8578, -3761},
            new long[]{130, -593},
            new long[]{130, -57},
            new long[]{2, -9},
            new long[]{2, -1},
            new long[]{-2, 1},
            new long[]{-2, 9},
            new long[]{-130, 57},
            new long[]{-130, 593},
            new long[]{-8578, 3761},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative22() {
        System.out.println("Representative 22: 2x^2 + 5xy + y^2 + 1 = 0 (D > 0)");
        int a = 2, b = 5, c = 1, d = 0, e = 0, f = 1;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, 1},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test31() {
        System.out.println("31: 2x^2 - 9y^2 + 601 = 0 (D > 0)");
        int a = 2, b = 0, c = -9, d = 0, e = 0, f = 601;

        long[][] expectedSolutions = new long[][]{
            new long[]{-6400, -3017},
            new long[]{6400, -3017},
            new long[]{-460, -217},
            new long[]{460, -217},
            new long[]{-188, -89},
            new long[]{188, -89},
            new long[]{-8, -9},
            new long[]{8, -9},
            new long[]{-8, 9},
            new long[]{8, 9},
            new long[]{-188, 89},
            new long[]{188, 89},
            new long[]{-460, 217},
            new long[]{460, 217},
            new long[]{-6400, 3017},
            new long[]{6400, 3017},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative31() {
        System.out.println("Representative 31: 2x^2 - 9y^2 + 601 = 0 (D > 0)");
        int a = 2, b = 0, c = -9, d = 0, e = 0, f = 601;

        long[][] expectedSolutions = new long[][]{
            new long[]{-8, 9},
            new long[]{8, 9},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test32() {
        System.out.println("32: -3x^2 - 8xy + 2y^2 - 87 = 0 (D > 0)");
        int a = -3, b = -8, c = 2, d = 0, e = 0, f = -87;

        long[][] expectedSolutions = new long[][]{
            new long[]{-785, -3411},
            new long[]{9881, -3411},
            new long[]{-391, -1699},
            new long[]{785, -271},
            new long[]{-31, -135},
            new long[]{391, -135},
            new long[]{-25, -109},
            new long[]{31, -11},
            new long[]{-1, -9},
            new long[]{25, -9},
            new long[]{1, -5},
            new long[]{-1, 5},
            new long[]{-25, 9},
            new long[]{1, 9},
            new long[]{-31, 11},
            new long[]{25, 109},
            new long[]{-391, 135},
            new long[]{31, 135},
            new long[]{-785, 271},
            new long[]{391, 1699},
            new long[]{-9881, 3411},
            new long[]{785, 3411},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative32() {
        System.out.println("Representative 32: -3x^2 - 8xy + 2y^2 - 87 = 0 (D > 0)");
        int a = -3, b = -8, c = 2, d = 0, e = 0, f = -87;

        long[][] expectedSolutions = new long[][]{
            new long[]{-1, 5},
            new long[]{-25, 9},
            new long[]{1, 9},
            new long[]{-31, 11},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test69() {
        System.out.println("69: 42x^2 + 62xy + 21y^2 - 585 = 0 (D > 0)");
        int a = 42, b = 62, c = 21, d = 0, e = 0, f = -585;

        long[][] expectedSolutions = new long[][]{
            new long[]{1587, -1671},
            new long[]{786, -1493},
            new long[]{1266, -1333},
            new long[]{627, -1191},
            new long[]{93, -177},
            new long[]{74, -141},
            new long[]{83, -87},
            new long[]{66, -69},
            new long[]{6, -3},
            new long[]{-3, -1},
            new long[]{3, 1},
            new long[]{-6, 3},
            new long[]{-66, 69},
            new long[]{-83, 87},
            new long[]{-74, 141},
            new long[]{-93, 177},
            new long[]{-627, 1191},
            new long[]{-1266, 1333},
            new long[]{-786, 1493},
            new long[]{-1587, 1671},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative69() {
        System.out.println("Representative 69: 42x^2 + 62xy + 21y^2 - 585 = 0 (D > 0)");
        int a = 42, b = 62, c = 21, d = 0, e = 0, f = -585;

        long[][] expectedSolutions = new long[][]{
            new long[]{3, 1},
            new long[]{-6, 3},
            new long[]{-66, 69},
            new long[]{-83, 87},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test70() {
        System.out.println("70: 2x^2 + 18xy + y^2 - 585 = 0 (D > 0)");
        int a = 2, b = 18, c = 1, d = 0, e = 0, f = -585;

        long[][] expectedSolutions = new long[][]{
            new long[]{84, -1503},
            new long[]{67, -1199},
            new long[]{4, -79},
            new long[]{707, -79},
            new long[]{3, -63},
            new long[]{564, -63},
            new long[]{-3, -9},
            new long[]{84, -9},
            new long[]{-4, -7},
            new long[]{67, -7},
            new long[]{-67, 7},
            new long[]{4, 7},
            new long[]{-84, 9},
            new long[]{3, 9},
            new long[]{-564, 63},
            new long[]{-3, 63},
            new long[]{-707, 79},
            new long[]{-4, 79},
            new long[]{-67, 1199},
            new long[]{-84, 1503},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative70() {
        System.out.println("Representative 70: 2x^2 + 18xy + y^2 - 585 = 0 (D > 0)");
        int a = 2, b = 18, c = 1, d = 0, e = 0, f = -585;

        long[][] expectedSolutions = new long[][]{
            new long[]{-67, 7},
            new long[]{4, 7},
            new long[]{-84, 9},
            new long[]{3, 9},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test71() {
        System.out.println("71: 3x^2 - 3xy - 2y^2 - 202 = 0 (D > 0)");
        int a = 3, b = -3, c = -2, d = 0, e = 0, f = -202;

        long[][] expectedSolutions = new long[][]{
            new long[]{-7583, -5203},
            new long[]{2380, -5203},
            new long[]{-2380, -1633},
            new long[]{747, -1633},
            new long[]{-1593, -1093},
            new long[]{500, -1093},
            new long[]{-500, -343},
            new long[]{157, -343},
            new long[]{-165, -113},
            new long[]{52, -113},
            new long[]{-52, -35},
            new long[]{17, -35},
            new long[]{-35, -23},
            new long[]{12, -23},
            new long[]{-12, -5},
            new long[]{7, -5},
            new long[]{-7, 5},
            new long[]{12, 5},
            new long[]{-12, 23},
            new long[]{35, 23},
            new long[]{-17, 35},
            new long[]{52, 35},
            new long[]{-52, 113},
            new long[]{165, 113},
            new long[]{-157, 343},
            new long[]{500, 343},
            new long[]{-500, 1093},
            new long[]{1593, 1093},
            new long[]{-747, 1633},
            new long[]{2380, 1633},
            new long[]{-2380, 5203},
            new long[]{7583, 5203},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative71() {
        System.out.println("Representative 71: 3x^2 - 3xy - 2y^2 - 202 = 0 (D > 0)");
        int a = 3, b = -3, c = -2, d = 0, e = 0, f = -202;

        long[][] expectedSolutions = new long[][]{
            new long[]{-7, 5},
            new long[]{12, 5},
            new long[]{-12, 23},
            new long[]{35, 23},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test72() {
        System.out.println("72: 19x^2 - 85xy + 95y^2 + 671 = 0 (D > 0)");
        int a = 19, b = -85, c = 95, d = 0, e = 0, f = 671;

        long[][] expectedSolutions = new long[][]{
            new long[]{-9886, -4539},
            new long[]{-8699, -3994},
            new long[]{-8471, -3690},
            new long[]{-7454, -3247},
            new long[]{-6274, -2733},
            new long[]{-5521, -2405},
            new long[]{-5101, -2342},
            new long[]{-4489, -2061},
            new long[]{-3779, -1735},
            new long[]{-3326, -1527},
            new long[]{-3239, -1411},
            new long[]{-2851, -1242},
            new long[]{-2401, -1046},
            new long[]{-2114, -921},
            new long[]{-1954, -897},
            new long[]{-1721, -790},
            new long[]{-1451, -666},
            new long[]{-1279, -587},
            new long[]{-1246, -543},
            new long[]{-1099, -479},
            new long[]{-929, -405},
            new long[]{-821, -358},
            new long[]{-761, -349},
            new long[]{-674, -309},
            new long[]{-574, -263},
            new long[]{-511, -234},
            new long[]{-499, -218},
            new long[]{-446, -195},
            new long[]{-386, -169},
            new long[]{-349, -153},
            new long[]{-329, -150},
            new long[]{-301, -137},
            new long[]{-271, -123},
            new long[]{-254, -115},
            new long[]{-251, -111},
            new long[]{-239, -106},
            new long[]{-229, -102},
            new long[]{-226, -101},
            new long[]{226, 101},
            new long[]{229, 102},
            new long[]{239, 106},
            new long[]{251, 111},
            new long[]{254, 115},
            new long[]{271, 123},
            new long[]{301, 137},
            new long[]{329, 150},
            new long[]{349, 153},
            new long[]{386, 169},
            new long[]{446, 195},
            new long[]{499, 218},
            new long[]{511, 234},
            new long[]{574, 263},
            new long[]{674, 309},
            new long[]{761, 349},
            new long[]{821, 358},
            new long[]{929, 405},
            new long[]{1099, 479},
            new long[]{1246, 543},
            new long[]{1279, 587},
            new long[]{1451, 666},
            new long[]{1721, 790},
            new long[]{1954, 897},
            new long[]{2114, 921},
            new long[]{2401, 1046},
            new long[]{2851, 1242},
            new long[]{3239, 1411},
            new long[]{3326, 1527},
            new long[]{3779, 1735},
            new long[]{4489, 2061},
            new long[]{5101, 2342},
            new long[]{5521, 2405},
            new long[]{6274, 2733},
            new long[]{7454, 3247},
            new long[]{8471, 3690},
            new long[]{8699, 3994},
            new long[]{9886, 4539},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative72() {
        System.out.println("Representative 72: 19x^2 - 85xy + 95y^2 + 671 = 0 (D > 0)");
        int a = 19, b = -85, c = 95, d = 0, e = 0, f = 671;

        long[][] expectedSolutions = new long[][]{
            new long[]{226, 101},
            new long[]{229, 102},
            new long[]{239, 106},
            new long[]{251, 111},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test73() {
        System.out.println("73: -3x^2 + 5xy + 5y^2 - 513 = 0 (D > 0)");
        int a = -3, b = 5, c = 5, d = 0, e = 0, f = -513;

        long[][] expectedSolutions = new long[][]{
            new long[]{4363, -6204},
            new long[]{3953, -5621},
            new long[]{-8572, -3617},
            new long[]{2012, -2861},
            new long[]{-4363, -1841},
            new long[]{-3953, -1668},
            new long[]{1173, -1668},
            new long[]{-2012, -849},
            new long[]{597, -849},
            new long[]{-1173, -495},
            new long[]{348, -495},
            new long[]{-597, -252},
            new long[]{177, -252},
            new long[]{-348, -147},
            new long[]{103, -147},
            new long[]{-177, -75},
            new long[]{52, -75},
            new long[]{47, -68},
            new long[]{-103, -44},
            new long[]{23, -35},
            new long[]{-52, -23},
            new long[]{-47, -21},
            new long[]{12, -21},
            new long[]{-23, -12},
            new long[]{3, -12},
            new long[]{-12, -9},
            new long[]{-3, -9},
            new long[]{3, 9},
            new long[]{12, 9},
            new long[]{-3, 12},
            new long[]{23, 12},
            new long[]{-12, 21},
            new long[]{47, 21},
            new long[]{52, 23},
            new long[]{-23, 35},
            new long[]{103, 44},
            new long[]{-47, 68},
            new long[]{-52, 75},
            new long[]{177, 75},
            new long[]{-103, 147},
            new long[]{348, 147},
            new long[]{-177, 252},
            new long[]{597, 252},
            new long[]{-348, 495},
            new long[]{1173, 495},
            new long[]{-597, 849},
            new long[]{2012, 849},
            new long[]{-1173, 1668},
            new long[]{3953, 1668},
            new long[]{4363, 1841},
            new long[]{-2012, 2861},
            new long[]{8572, 3617},
            new long[]{-3953, 5621},
            new long[]{-4363, 6204},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative73() {
        System.out.println("Representative 73: -3x^2 + 5xy + 5y^2 - 513 = 0 (D > 0)");
        int a = -3, b = 5, c = 5, d = 0, e = 0, f = -513;

        long[][] expectedSolutions = new long[][]{
            new long[]{3, 9},
            new long[]{12, 9},
            new long[]{-3, 12},
            new long[]{23, 12},
            new long[]{-12, 21},
            new long[]{47, 21},
            new long[]{52, 23},
            new long[]{-23, 35},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test74() {
        System.out.println("74: 6x^2 + 2xy - 3y^2 - 25 = 0 (D > 0)");
        int a = 6, b = 2, c = -3, d = 0, e = 0, f = -25;

        long[][] expectedSolutions = new long[][]{
            new long[]{8827, -9883},
            new long[]{-4394, -7849},
            new long[]{1630, -1825},
            new long[]{-379, -677},
            new long[]{301, -337},
            new long[]{-70, -125},
            new long[]{26, -29},
            new long[]{-13, -23},
            new long[]{5, -5},
            new long[]{-2, -1},
            new long[]{2, 1},
            new long[]{-5, 5},
            new long[]{13, 23},
            new long[]{-26, 29},
            new long[]{70, 125},
            new long[]{-301, 337},
            new long[]{379, 677},
            new long[]{-1630, 1825},
            new long[]{4394, 7849},
            new long[]{-8827, 9883},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative74() {
        System.out.println("Representative 74: 6x^2 + 2xy - 3y^2 - 25 = 0 (D > 0)");
        int a = 6, b = 2, c = -3, d = 0, e = 0, f = -25;

        long[][] expectedSolutions = new long[][]{
            new long[]{2, 1},
            new long[]{-5, 5},
            new long[]{13, 23},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test75() {
        System.out.println("75: 6x^2 - 8xy - 5y^2 + 56 = 0 (D > 0)");
        int a = 6, b = -8, c = -5, d = 0, e = 0, f = 56;

        long[][] expectedSolutions = new long[][]{
            new long[]{-726, -404},
            new long[]{102, -220},
            new long[]{-6, -4},
            new long[]{6, 4},
            new long[]{-102, 220},
            new long[]{726, 404},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative75() {
        System.out.println("Representative 75: 6x^2 - 8xy - 5y^2 + 56 = 0 (D > 0)");
        int a = 6, b = -8, c = -5, d = 0, e = 0, f = 56;

        long[][] expectedSolutions = new long[][]{
            new long[]{6, 4},
            new long[]{-102, 220},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test76() {
        System.out.println("76: 2x^2 - 8xy + 7y^2 - 369 = 0 (D > 0)");
        int a = 2, b = -8, c = 7, d = 0, e = 0, f = -369;

        long[][] expectedSolutions = new long[][]{
            new long[]{-9111, -7047},
            new long[]{-8097, -2991},
            new long[]{-3867, -2991},
            new long[]{-3273, -1209},
            new long[]{-1563, -1209},
            new long[]{-1389, -513},
            new long[]{-663, -513},
            new long[]{-561, -207},
            new long[]{-267, -207},
            new long[]{-237, -87},
            new long[]{-111, -87},
            new long[]{-93, -33},
            new long[]{-39, -33},
            new long[]{-33, -9},
            new long[]{-3, -9},
            new long[]{3, 9},
            new long[]{33, 9},
            new long[]{39, 33},
            new long[]{93, 33},
            new long[]{111, 87},
            new long[]{237, 87},
            new long[]{267, 207},
            new long[]{561, 207},
            new long[]{663, 513},
            new long[]{1389, 513},
            new long[]{1563, 1209},
            new long[]{3273, 1209},
            new long[]{3867, 2991},
            new long[]{8097, 2991},
            new long[]{9111, 7047},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative76() {
        System.out.println("Representative 76: 2x^2 - 8xy + 7y^2 - 369 = 0 (D > 0)");
        int a = 2, b = -8, c = 7, d = 0, e = 0, f = -369;

        long[][] expectedSolutions = new long[][]{
            new long[]{3, 9},
            new long[]{33, 9},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test77() {
        System.out.println("77: 9x^2 - 12xy - 27y^2 + 471 = 0 (D > 0)");
        int a = 9, b = -12, c = -27, d = 0, e = 0, f = 471;

        long[][] expectedSolutions = new long[][]{
            new long[]{8854, -7445},
            new long[]{-1009, -400},
            new long[]{34, -29},
            new long[]{-1, -4},
            new long[]{1, 4},
            new long[]{-34, 29},
            new long[]{1009, 400},
            new long[]{-8854, 7445},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative77() {
        System.out.println("Representative 77: 9x^2 - 12xy - 27y^2 + 471 = 0 (D > 0)");
        int a = 9, b = -12, c = -27, d = 0, e = 0, f = 471;

        long[][] expectedSolutions = new long[][]{
            new long[]{1, 4},
            new long[]{-34, 29},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test78() {
        System.out.println("78: 3x^2 + 28xy + 24y^2 - 2007963 = 0 (D > 0)");
        int a = 3, b = 28, c = 24, d = 0, e = 0, f = -2007963;

        long[][] expectedSolutions = new long[][]{
            new long[]{80101, -83892},
            new long[]{37761, -39550},
            new long[]{7809, -8190},
            new long[]{68631, -8190},
            new long[]{4281, -490},
            new long[]{-189, -192},
            new long[]{1981, -192},
            new long[]{-1981, 192},
            new long[]{189, 192},
            new long[]{-4281, 490},
            new long[]{-68631, 8190},
            new long[]{-7809, 8190},
            new long[]{-37761, 39550},
            new long[]{-80101, 83892},
            new long[]{-702891, 83892},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative78() {
        System.out.println("Representative 78: 3x^2 + 28xy + 24y^2 - 2007963 = 0 (D > 0)");
        int a = 3, b = 28, c = 24, d = 0, e = 0, f = -2007963;

        long[][] expectedSolutions = new long[][]{
            new long[]{-1981, 192},
            new long[]{189, 192},
            new long[]{-4281, 490},
            new long[]{-68631, 8190},
            new long[]{-7809, 8190},
            new long[]{-37761, 39550},
            new long[]{-80101, 83892},
            new long[]{-702891, 83892},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test79() {
        System.out.println("79: 7x^2 - xy - 4y^2 - 580594 = 0 (D > 0)");
        int a = 7, b = -1, c = -4, d = 0, e = 0, f = -580594;

        long[][] expectedSolutions = new long[][]{
            new long[]{-39694, -47781},
            new long[]{-21326, -25669},
            new long[]{17659, -25669},
            new long[]{3986, -5781},
            new long[]{-709, -773},
            new long[]{-421, -357},
            new long[]{370, -357},
            new long[]{-370, 357},
            new long[]{421, 357},
            new long[]{709, 773},
            new long[]{-3986, 5781},
            new long[]{-17659, 25669},
            new long[]{21326, 25669},
            new long[]{39694, 47781},
            new long[]{212635, 255963},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative79() {
        System.out.println("Representative 79: 7x^2 - xy - 4y^2 - 580594 = 0 (D > 0)");
        int a = 7, b = -1, c = -4, d = 0, e = 0, f = -580594;

        long[][] expectedSolutions = new long[][]{
            new long[]{-370, 357},
            new long[]{421, 357},
            new long[]{709, 773},
            new long[]{-3986, 5781},
            new long[]{-17659, 25669},
            new long[]{21326, 25669},
            new long[]{39694, 47781},
            new long[]{212635, 255963},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test81() {
        System.out.println("81: 4x^2 + 4xy - 4y^2 - 2 = 0 (D > 0)");
        int a = 4, b = 4, c = -4, d = 0, e = 0, f = -2;

        TestUtils.assertNoSolutions(a, b, c, d, e, f, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative81() {
        System.out.println("Representative 81: 4x^2 + 4xy - 4y^2 - 2 = 0 (D > 0)");
        int a = 4, b = 4, c = -4, d = 0, e = 0, f = -2;

        assertTrue(RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)).isEmpty());
    }
    
    @Test
    public void test82() {
        System.out.println("82: x^2 - 60y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -60, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-3842, -496},
            new long[]{3842, -496},
            new long[]{-488, -63},
            new long[]{488, -63},
            new long[]{-62, -8},
            new long[]{62, -8},
            new long[]{-8, -1},
            new long[]{8, -1},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-8, 1},
            new long[]{8, 1},
            new long[]{-62, 8},
            new long[]{62, 8},
            new long[]{-488, 63},
            new long[]{488, 63},
            new long[]{-3842, 496},
            new long[]{3842, 496},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }
    
    @Test
    public void testRepresentative82() {
        System.out.println("Representative 82: x^2 - 60y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -60, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, 0},
            new long[]{-8, 1},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test83() {
        System.out.println("83: x^2 + xy - y^2 + 1 = 0 (D > 0)");
        int a = 1, b = 1, c = -1, d = 0, e = 0, f = 1;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2584, -4181},
            new long[]{6765, -4181},
            new long[]{-987, -1597},
            new long[]{2584, -1597},
            new long[]{-377, -610},
            new long[]{987, -610},
            new long[]{-144, -233},
            new long[]{377, -233},
            new long[]{-55, -89},
            new long[]{144, -89},
            new long[]{-21, -34},
            new long[]{55, -34},
            new long[]{-8, -13},
            new long[]{21, -13},
            new long[]{-3, -5},
            new long[]{8, -5},
            new long[]{-1, -2},
            new long[]{3, -2},
            new long[]{0, -1},
            new long[]{1, -1},
            new long[]{-1, 1},
            new long[]{0, 1},
            new long[]{-3, 2},
            new long[]{1, 2},
            new long[]{-8, 5},
            new long[]{3, 5},
            new long[]{-21, 13},
            new long[]{8, 13},
            new long[]{-55, 34},
            new long[]{21, 34},
            new long[]{-144, 89},
            new long[]{55, 89},
            new long[]{-377, 233},
            new long[]{144, 233},
            new long[]{-987, 610},
            new long[]{377, 610},
            new long[]{-2584, 1597},
            new long[]{987, 1597},
            new long[]{-6765, 4181},
            new long[]{2584, 4181},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative83() {
        System.out.println("Representative 83: x^2 + xy - y^2 + 1 = 0 (D > 0)");
        int a = 1, b = 1, c = -1, d = 0, e = 0, f = 1;

        long[][] expectedSolutions = new long[][]{
            new long[]{-1, 1},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }

    @Test
    public void test84() {
        System.out.println("84: x^2 + 4xy - 4y^2 - 1 = 0 (D > 0)");
        int a = 1, b = 4, c = -4, d = 0, e = 0, f = -1;

        long[][] expectedSolutions = new long[][]{
            new long[]{-5741, -6930},
            new long[]{-985, -1189},
            new long[]{5741, -1189},
            new long[]{-169, -204},
            new long[]{985, -204},
            new long[]{-29, -35},
            new long[]{169, -35},
            new long[]{-5, -6},
            new long[]{29, -6},
            new long[]{-1, -1},
            new long[]{5, -1},
            new long[]{-1, 0},
            new long[]{1, 0},
            new long[]{-5, 1},
            new long[]{1, 1},
            new long[]{-29, 6},
            new long[]{5, 6},
            new long[]{-169, 35},
            new long[]{29, 35},
            new long[]{-985, 204},
            new long[]{169, 204},
            new long[]{-5741, 1189},
            new long[]{985, 1189},
            new long[]{5741, 6930},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative84() {
        System.out.println("Representative 84: x^2 + 4xy - 4y^2 - 1 = 0 (D > 0)");
        int a = 1, b = 4, c = -4, d = 0, e = 0, f = -1;

        long[][] expectedSolutions = new long[][]{
            new long[]{-1, 0},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }
    
    @Test
    public void test85() {
        System.out.println("85: x^2 + 5xy - 3y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 5, c = -3, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-3794, -7008},
            new long[]{-26, -48},
            new long[]{266, -48},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-266, 48},
            new long[]{26, 48},
            new long[]{3794, 7008},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative85() {
        System.out.println("Representative 85: x^2 + 5xy - 3y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 5, c = -3, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-2, 0},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
    }

    @Test
    public void test86() {
        System.out.println("86: x^2 + 3xy + y^2 + 4 = 0 (D > 0)");
        int a = 1, b = 3, c = 1, d = 0, e = 0, f = 4;

        long[][] expectedSolutions = new long[][]{
            new long[]{3194, -8362},
            new long[]{1220, -3194},
            new long[]{8362, -3194},
            new long[]{466, -1220},
            new long[]{3194, -1220},
            new long[]{178, -466},
            new long[]{1220, -466},
            new long[]{68, -178},
            new long[]{466, -178},
            new long[]{26, -68},
            new long[]{178, -68},
            new long[]{10, -26},
            new long[]{68, -26},
            new long[]{4, -10},
            new long[]{26, -10},
            new long[]{2, -4},
            new long[]{10, -4},
            new long[]{2, -2},
            new long[]{4, -2},
            new long[]{-4, 2},
            new long[]{-2, 2},
            new long[]{-10, 4},
            new long[]{-2, 4},
            new long[]{-26, 10},
            new long[]{-4, 10},
            new long[]{-68, 26},
            new long[]{-10, 26},
            new long[]{-178, 68},
            new long[]{-26, 68},
            new long[]{-466, 178},
            new long[]{-68, 178},
            new long[]{-1220, 466},
            new long[]{-178, 466},
            new long[]{-3194, 1220},
            new long[]{-466, 1220},
            new long[]{-8362, 3194},
            new long[]{-1220, 3194},
            new long[]{-3194, 8362},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.solve(a, b, c, f));
    }

    @Test
    public void testRepresentative86() {
        System.out.println("Representative 86: x^2 + 3xy + y^2 + 4 = 0 (D > 0)");
        int a = 1, b = 3, c = 1, d = 0, e = 0, f = 4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-4, 2},
        };

        validateRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions);
        assertRepresentativeSolutions(a, b, c, d, e, f, expectedSolutions, RestrictedHyperbolicSolver.getRepresentativeSolutions(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f)));
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
