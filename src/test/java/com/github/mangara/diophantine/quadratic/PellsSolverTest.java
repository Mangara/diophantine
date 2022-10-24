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
import java.math.BigInteger;
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
        TestUtils.assertNotSupportedYet(() -> { PellsSolver.solvePellsFour(BigInteger.valueOf(-c)); });
        //TestUtils.assertSolutionsInclude(a, b, c, d, e, f, expectedSolutions, PellsSolver.solvePellsFour(BigInteger.valueOf(-c)));
    }
    
}
