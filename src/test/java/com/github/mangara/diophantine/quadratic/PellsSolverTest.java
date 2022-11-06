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
import org.junit.jupiter.api.Test;

public class PellsSolverTest {
    
    public PellsSolverTest() {
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
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }
    
    @Test
    public void pellsFourTest02() {
        System.out.println("2: x^2 - 19y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -19, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-340, -78},
            new long[]{340, -78},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-340, 78},
            new long[]{340, 78},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest03() {
        System.out.println("3: x^2 - 221y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -221, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-3330, -224},
            new long[]{3330, -224},
            new long[]{-223, -15},
            new long[]{223, -15},
            new long[]{-15, -1},
            new long[]{15, -1},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-15, 1},
            new long[]{15, 1},
            new long[]{-223, 15},
            new long[]{223, 15},
            new long[]{-3330, 224},
            new long[]{3330, 224},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest04() {
        System.out.println("4: x^2 - 17y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -17, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-4354, -1056},
            new long[]{4354, -1056},
            new long[]{-66, -16},
            new long[]{66, -16},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-66, 16},
            new long[]{66, 16},
            new long[]{-4354, 1056},
            new long[]{4354, 1056},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest05() {
        System.out.println("5: x^2 - 40967y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -40967, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-5180304, -25594},
            new long[]{5180304, -25594},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-5180304, 25594},
            new long[]{5180304, 25594},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest06() {
        System.out.println("6: x^2 - 20560y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -20560, d = 0, e = 0, f = -4;

        BigInteger expectedX = new BigInteger("308132436864934200522212498"), expectedY = new BigInteger("2148947786232470755088550");

        List<XYPair> expectedSolutions = Arrays.asList(
            new XYPair(expectedX.negate(), expectedY.negate()),
            new XYPair(expectedX, expectedY.negate()),
            new XYPair(BigInteger.TWO.negate(), BigInteger.ZERO),
            new XYPair(BigInteger.TWO, BigInteger.ZERO),
            new XYPair(expectedX.negate(), expectedY),
            new XYPair(expectedX, expectedY)
        );

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest07() {
        System.out.println("7: x^2 - 16099y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -16099, d = 0, e = 0, f = -4;

        BigInteger expectedX = new BigInteger("536013903225608613608618698359084983422812580"), expectedY = new BigInteger("4224512548288545043060461503701143206910498");

        List<XYPair> expectedSolutions = Arrays.asList(
            new XYPair(expectedX.negate(), expectedY.negate()),
            new XYPair(expectedX, expectedY.negate()),
            new XYPair(BigInteger.TWO.negate(), BigInteger.ZERO),
            new XYPair(BigInteger.TWO, BigInteger.ZERO),
            new XYPair(expectedX.negate(), expectedY),
            new XYPair(expectedX, expectedY)
        );

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest08() {
        System.out.println("8: x^2 - 2848y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -2848, d = 0, e = 0, f = -4;

        long[][] expectedSolutions = new long[][]{
            new long[]{-3202, -60},
            new long[]{3202, -60},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-3202, 60},
            new long[]{3202, 60},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest09() {
        System.out.println("9: x^2 - 29512y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -29512, d = 0, e = 0, f = -4;

        BigInteger expectedX = new BigInteger("313346"), expectedY = new BigInteger("1824");

        List<XYPair> expectedSolutions = Arrays.asList(
            new XYPair(expectedX.negate(), expectedY.negate()),
            new XYPair(expectedX, expectedY.negate()),
            new XYPair(BigInteger.TWO.negate(), BigInteger.ZERO),
            new XYPair(BigInteger.TWO, BigInteger.ZERO),
            new XYPair(expectedX.negate(), expectedY),
            new XYPair(expectedX, expectedY)
        );

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest10() {
        System.out.println("10: x^2 - 14335y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -14335, d = 0, e = 0, f = -4;

        BigInteger expectedX = new BigInteger("2179380721066856132942"), expectedY = new BigInteger("18202634809601380296");

        List<XYPair> expectedSolutions = Arrays.asList(
            new XYPair(expectedX.negate(), expectedY.negate()),
            new XYPair(expectedX, expectedY.negate()),
            new XYPair(BigInteger.TWO.negate(), BigInteger.ZERO),
            new XYPair(BigInteger.TWO, BigInteger.ZERO),
            new XYPair(expectedX.negate(), expectedY),
            new XYPair(expectedX, expectedY)
        );

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest11() {
        System.out.println("11: x^2 - 29986y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -29986, d = 0, e = 0, f = -4;

        BigInteger expectedX = new BigInteger("135915148103491619905402044543098"), expectedY = new BigInteger("784889635731418443294120995460");

        List<XYPair> expectedSolutions = Arrays.asList(
            new XYPair(expectedX.negate(), expectedY.negate()),
            new XYPair(expectedX, expectedY.negate()),
            new XYPair(BigInteger.TWO.negate(), BigInteger.ZERO),
            new XYPair(BigInteger.TWO, BigInteger.ZERO),
            new XYPair(expectedX.negate(), expectedY),
            new XYPair(expectedX, expectedY)
        );

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest12() {
        System.out.println("12: x^2 - 12412y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -12412, d = 0, e = 0, f = -4;

        BigInteger expectedX = new BigInteger("1735334650280994091954"), expectedY = new BigInteger("15576230136070851324");

        List<XYPair> expectedSolutions = Arrays.asList(
            new XYPair(expectedX.negate(), expectedY.negate()),
            new XYPair(expectedX, expectedY.negate()),
            new XYPair(BigInteger.TWO.negate(), BigInteger.ZERO),
            new XYPair(BigInteger.TWO, BigInteger.ZERO),
            new XYPair(expectedX.negate(), expectedY),
            new XYPair(expectedX, expectedY)
        );

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest13() {
        System.out.println("13: x^2 - 19781y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -19781, d = 0, e = 0, f = -4;

        long expectedX = 129877126560277L, expectedY = 923439718215L;

        long[][] expectedSolutions = new long[][]{
            new long[]{-expectedX, -expectedY},
            new long[]{expectedX, -expectedY},
            new long[]{-2, 0},
            new long[]{2, 0},
            new long[]{-expectedX, expectedY},
            new long[]{expectedX, expectedY},
        };

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }

    @Test
    public void pellsFourTest14() {
        System.out.println("14: x^2 - 36236y^2 - 4 = 0 (D > 0)");
        int a = 1, b = 0, c = -36236, d = 0, e = 0, f = -4;

        BigInteger expectedX = new BigInteger("547012614465273295091694381107672500"), expectedY = new BigInteger("2873605962195725402739521607990369");

        List<XYPair> expectedSolutions = Arrays.asList(
            new XYPair(expectedX.negate(), expectedY.negate()),
            new XYPair(expectedX, expectedY.negate()),
            new XYPair(BigInteger.TWO.negate(), BigInteger.ZERO),
            new XYPair(BigInteger.TWO, BigInteger.ZERO),
            new XYPair(expectedX.negate(), expectedY),
            new XYPair(expectedX, expectedY)
        );

        TestUtils.validateExpectedSolutions(a, b, c, d, e, f, expectedSolutions);
        TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }
    
}
