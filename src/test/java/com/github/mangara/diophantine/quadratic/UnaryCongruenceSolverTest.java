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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnaryCongruenceSolverTest {
    
    public UnaryCongruenceSolverTest() {
    }

    @Test
    public void testSolve0() {
        List<BigInteger> expectedSolutions = integers(0);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solve(6, 14, 8, 1));
    }
    
    @Test
    public void testSolve1() {
        List<BigInteger> expectedSolutions = integers(8, 20);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solve(6, 14, 8, 21));
    }
    
    @Test
    public void testSolve2() {
        List<BigInteger> expectedSolutions = integers(5, 20);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solve(18, 5, 8, 21));
    }
    
    @Test
    public void testSolve3() {
        List<BigInteger> expectedSolutions = integers(17, 61, 82, 126);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solve(1, 0, -3, 143));
    }
    
    @Test
    public void testSolve4() {
        List<BigInteger> expectedSolutions = integers(19, 82);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solve(1, 0, -58, 101));
    }
    
    @Test
    public void testSolve5() {
        List<BigInteger> expectedSolutions = integers(26, 87);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solve(1, 0, -111, 113));
    }
    
    @Test
    public void testSolveReduced3() {
        List<BigInteger> expectedSolutions = integers(17, 61, 82, 126);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solveReduced(1, -3, 143));
    }
    
    @Test
    public void testSolveReduced4() {
        List<BigInteger> expectedSolutions = integers(19, 82);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solveReduced(1, -58, 101));
    }
    
    @Test
    public void testSolveReduced5() {
        List<BigInteger> expectedSolutions = integers(26, 87);
        assertEquals(expectedSolutions, UnaryCongruenceSolver.solveReduced(1, -111, 113));
    }
    
    private List<BigInteger> integers(long... numbers) {
        return Arrays.stream(numbers).mapToObj(n -> BigInteger.valueOf(n)).collect(Collectors.toList());
    }
}
