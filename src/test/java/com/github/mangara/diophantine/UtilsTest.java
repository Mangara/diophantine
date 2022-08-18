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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    
    public UtilsTest() {
    }

    @Test
    public void testGcd() {
        System.out.println("gcd");
        assertEquals(23, Utils.gcd(1235675, 180859534));
        assertEquals(23, Utils.gcd(1235675L, 180859534L));
    }
    
    @Test
    public void testGcdSign() {
        System.out.println("gcd is always positive");
        
        assertEquals(5, Utils.gcd(15, 25));
        assertEquals(5, Utils.gcd(-15, 25));
        assertEquals(5, Utils.gcd(15, -25));
        assertEquals(5, Utils.gcd(-15, -25));
        
        assertEquals(5, Utils.gcd(15L, 25L));
        assertEquals(5, Utils.gcd(-15L, 25L));
        assertEquals(5, Utils.gcd(15L, -25L));
        assertEquals(5, Utils.gcd(-15L, -25L));
    }

    @Test
    public void testMultiGcd() {
        System.out.println("multi-gcd");
        assertEquals(2, Utils.gcd(57219486, 38139166, 18777898, 80761648));
        assertEquals(2, Utils.gcd(57219486L, 38139166L, 18777898L, 80761648L));
    }
    
    @Test
    public void testIsSquare() {
        System.out.println("isSquare");
        assertFalse(Utils.isSquare(-1));
        assertTrue(Utils.isSquare(0));
        assertTrue(Utils.isSquare(1));
        assertFalse(Utils.isSquare(2));
        assertFalse(Utils.isSquare(3));
        assertTrue(Utils.isSquare(4));
        assertFalse(Utils.isSquare(5));
    }
    
    @Test
    public void testDiscriminant() {
        System.out.println("discriminant");
        assertEquals(-20, Utils.discriminant(3, -8, 7));
        assertEquals(19976449, Utils.discriminant(1077, 5993, 3700));
    }
    
    @Test
    public void testLegendreConstant() {
        System.out.println("legendreConstant");
        int a = 8, b = -4, c = -8, d = -9, e = -9, f = -65;
        long D = Utils.discriminant(a, b, c);
        assertEquals(4720832, Utils.legendreConstant(a, b, c, d, e, f, D));
    }
    
    @Test
    public void testLegendreAlpha() {
        System.out.println("legendreAlpha");
        int a = 8, b = -4, c = -8, d = -9, e = -9, f = -65;
        assertEquals(108, Utils.legendreAlpha(b, c, d, e));
    }
    
    @Test
    public void testLegendreBeta() {
        System.out.println("legendreBeta");
        int a = 8, b = -4, c = -8, d = -9, e = -9, f = -65;
        assertEquals(-180, Utils.legendreBeta(a, b, d, e));
    }
    
}
