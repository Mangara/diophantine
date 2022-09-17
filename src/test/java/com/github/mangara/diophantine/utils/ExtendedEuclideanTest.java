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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExtendedEuclideanTest {
    
    public ExtendedEuclideanTest() {
    }

    @Test
    public void testGcdPair1() {
        System.out.println("gcdPair1");
        // As an example, the greatest common divisor of 15 and 69 is 3, and 3 can be written as a combination of 15 and 69 as 3 = 15 × (−9) + 69 × 2, with Bézout coefficients −9 and 2.
        BigInteger a = BigInteger.valueOf(15);
        BigInteger b = BigInteger.valueOf(69);
        XYPair expResult = new XYPair(-9, 2);
        XYPair result = ExtendedEuclidean.gcdPair(a, b);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGcdPair2() {
        System.out.println("gcdPair2");
        BigInteger a = BigInteger.valueOf(12);
        BigInteger b = BigInteger.valueOf(42);
        XYPair expResult = new XYPair(-3, 1);
        XYPair result = ExtendedEuclidean.gcdPair(a, b);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGcdPair3() {
        System.out.println("gcdPair3");
        BigInteger a = BigInteger.valueOf(42);
        BigInteger b = BigInteger.valueOf(13);
        XYPair expResult = new XYPair(-4, 13);
        XYPair result = ExtendedEuclidean.gcdPair(a, b);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGcdPair4() {
        System.out.println("gcdPair4");
        BigInteger a = BigInteger.valueOf(13);
        BigInteger b = BigInteger.valueOf(42);
        XYPair expResult = new XYPair(13, -4);
        XYPair result = ExtendedEuclidean.gcdPair(a, b);
        assertEquals(expResult, result);
    }
    
}
