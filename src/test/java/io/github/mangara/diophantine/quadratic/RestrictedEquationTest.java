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

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestrictedEquationTest {

    public RestrictedEquationTest() {
    }

    @Test
    public void testWithoutCommonDivisorRemovesCommonFactors() {
        System.out.println("withoutCommonDivisor removes common factors");
        
        RestrictedEquation eq = new RestrictedEquation(3, 6, 9, 12).withoutCommonDivisor();
        assertEquals(BigInteger.valueOf(1), eq.a);
        assertEquals(BigInteger.valueOf(2), eq.b);
        assertEquals(BigInteger.valueOf(3), eq.c);
        assertEquals(BigInteger.valueOf(4), eq.f);
        
        eq = new RestrictedEquation(7000, 0, 3300, 8200).withoutCommonDivisor();
        assertEquals(BigInteger.valueOf(70), eq.a);
        assertEquals(BigInteger.valueOf(0), eq.b);
        assertEquals(BigInteger.valueOf(33), eq.c);
        assertEquals(BigInteger.valueOf(82), eq.f);
    }
    
    @Test
    public void testWithoutCommonDivisorDetectsNoSolutionCase() {
        System.out.println("withoutCommonDivisor returns no solution if gcd(a, b, c) does not didive f");
        
        RestrictedEquation eq = new RestrictedEquation(3, 6, 9, 7).withoutCommonDivisor();
        assertEquals(RestrictedEquation.NO_SOLUTIONS, eq);
        
        eq = new RestrictedEquation(7000, 0, 3300, 8201).withoutCommonDivisor();
        assertEquals(RestrictedEquation.NO_SOLUTIONS, eq);
    }
    
    @Test
    public void testWithoutCommonDivisorIsIdempotent() {
        System.out.println("withoutCommonDivisor returns original equation if gcd(a, b, c) is 1");
        
        RestrictedEquation eq = new RestrictedEquation(3, 5, 6, 9).withoutCommonDivisor();
        assertEquals(BigInteger.valueOf(3), eq.a);
        assertEquals(BigInteger.valueOf(5), eq.b);
        assertEquals(BigInteger.valueOf(6), eq.c);
        assertEquals(BigInteger.valueOf(9), eq.f);
        
        eq = new RestrictedEquation(7000, 7, 3300, 8200).withoutCommonDivisor();
        assertEquals(BigInteger.valueOf(7000), eq.a);
        assertEquals(BigInteger.valueOf(7), eq.b);
        assertEquals(BigInteger.valueOf(3300), eq.c);
        assertEquals(BigInteger.valueOf(8200), eq.f);
    }
}
