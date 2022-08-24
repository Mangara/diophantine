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

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimesTest {
    
    public PrimesTest() {
    }

    @Test
    public void testGetPrimeFactors() {
        System.out.println("getPrimeFactors");
        
        assertEquals(Arrays.asList(), Primes.getPrimeFactors(1));
        assertEquals(Arrays.asList(2L), Primes.getPrimeFactors(2));
        assertEquals(Arrays.asList(2L, 3L), Primes.getPrimeFactors(6));
        assertEquals(Arrays.asList(2L, 2L, 2L, 2L, 3L, 3L, 5L, 7L, 11L), Primes.getPrimeFactors(55440));
        assertEquals(Arrays.asList(10111L), Primes.getPrimeFactors(10111));
    }
    
}
