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
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DivisorsTest {
    
    public DivisorsTest() {
    }

    @Test
    public void testGetDivisors() {
        System.out.println("getDivisors");
        
        assertEquals(Arrays.asList(1L, 2L), Divisors.getDivisors(2));
        
        assertEquals(Arrays.asList(1L), Divisors.getDivisors(1));
        
        List<Long> expected170 = Arrays.asList(1L, 2L, 5L, 10L, 17L, 34L, 85L, 170L);
        assertEquals(expected170, Divisors.getDivisors(170));
    }

    @Test
    public void testGetPositiveAndNegativeDivisors() {
        System.out.println("getPositiveAndNegativeDivisors");
        
        assertEquals(Arrays.asList(1L, -1L, 2L, -2L), Divisors.getPositiveAndNegativeDivisors(2));
        
        assertEquals(Arrays.asList(1L, -1L), Divisors.getPositiveAndNegativeDivisors(-1));
        assertEquals(Arrays.asList(1L, -1L), Divisors.getPositiveAndNegativeDivisors(1));
        
        List<Long> expected170 = Arrays.asList(1L, -1L, 2L, -2L, 5L, -5L, 10L, -10L, 17L, -17L, 34L, -34L, 85L, -85L, 170L, -170L);
        assertEquals(expected170, Divisors.getPositiveAndNegativeDivisors(170));
        assertEquals(expected170, Divisors.getPositiveAndNegativeDivisors(-170));
    }
    
    @Test
    public void testGetSquareDivisors() {
        System.out.println("getSquareDivisors");
        
        assertEquals(Arrays.asList(1L), Divisors.getSquareDivisors(1));
        assertEquals(Arrays.asList(1L), Divisors.getSquareDivisors(2));
        assertEquals(Arrays.asList(1L), Divisors.getSquareDivisors(170));
        
        List<Long> expected55440 = Arrays.asList(1L, 4L, 16L, 9L, 36L, 144L);
        assertEquals(expected55440, Divisors.getSquareDivisors(55440));
    }
    
}
