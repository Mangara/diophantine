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
package com.github.mangara.diophantine.utils;

import com.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChineseRemainderTest {
    
    public ChineseRemainderTest() {
    }

        @Test
    public void testSolveSystem1() {
        // Sun-tzu's original formulation: x ≡ 2 (mod 3) ≡ 3 (mod 5) ≡ 2 (mod 7) with the solution x = 23 + 105k, with k an integer
        System.out.println("solveSystem1");
        List<XYPair> remainders = Arrays.asList(
                new XYPair(2, 3),
                new XYPair(3, 5),
                new XYPair(2, 7)
                );
        BigInteger expResult = BigInteger.valueOf(23);
        BigInteger result = ChineseRemainder.solveSystem(remainders);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSolveSystem2() {
        // Wiki example: x ≡ 0 (mod 3) ≡ 3 (mod 4) ≡ 4 (mod 5) with the solution x = 39
        System.out.println("solveSystem2");
        List<XYPair> remainders = Arrays.asList(
                new XYPair(0, 3),
                new XYPair(3, 4),
                new XYPair(4, 5)
                );
        BigInteger expResult = BigInteger.valueOf(39);
        BigInteger result = ChineseRemainder.solveSystem(remainders);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSolveSystem3() {
        // x ≡ 2 (mod 5) ≡ 3 (mod 7) with the solution x = 17
        System.out.println("solveSystem3");
        List<XYPair> remainders = Arrays.asList(
                new XYPair(2, 5),
                new XYPair(3, 7)
                );
        BigInteger expResult = BigInteger.valueOf(17);
        BigInteger result = ChineseRemainder.solveSystem(remainders);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSolveSystem4() {
        // x ≡ 2 (mod 5) ≡ 3 (mod 7) with the solution x = 17
        System.out.println("solveSystem4");
        List<XYPair> remainders = Arrays.asList(
                new XYPair(3, 7),
                new XYPair(2, 5)
                );
        BigInteger expResult = BigInteger.valueOf(17);
        BigInteger result = ChineseRemainder.solveSystem(remainders);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSolveSystem5() {
        System.out.println("solveSystem5");
        List<XYPair> remainders = Arrays.asList(
                new XYPair(12, 19),
                new XYPair(9, 103),
                new XYPair(1, 23),
                new XYPair(19, 25),
                new XYPair(70, 89),
                new XYPair(185, 188),
                new XYPair(170, 173)
                );
        BigInteger expResult = BigInteger.valueOf(3227311083969L);
        BigInteger result = ChineseRemainder.solveSystem(remainders);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSolveSystem6() {
        System.out.println("solveSystem6");
        List<XYPair> remainders = Arrays.asList(
                new XYPair(35, 85),
                new XYPair(31, 199),
                new XYPair(118, 137),
                new XYPair(43, 59),
                new XYPair(119, 171),
                new XYPair(84, 157),
                new XYPair(18, 173),
                new XYPair(54, 191)
                );
        BigInteger expResult = BigInteger.valueOf(120422905246407350L);
        BigInteger result = ChineseRemainder.solveSystem(remainders);
        assertEquals(expResult, result);
    }
    
}
