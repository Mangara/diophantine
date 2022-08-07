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
    }
    
    @Test
    public void testGcdSign() {
        System.out.println("gcd is always positive");
        assertEquals(5, Utils.gcd(15, 25));
        assertEquals(5, Utils.gcd(-15, 25));
        assertEquals(5, Utils.gcd(15, -25));
        assertEquals(5, Utils.gcd(-15, -25));
    }

    @Test
    public void testMultiGcd() {
        System.out.println("multi-gcd");
        assertEquals(2, Utils.gcd(57219486, 38139166, 18777898, 80761648));
    }
    
}
