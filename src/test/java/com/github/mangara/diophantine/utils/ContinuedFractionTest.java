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
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContinuedFractionTest {
    
    public ContinuedFractionTest() {
    }

    @Test
    public void testGetCoefficients() {
        ContinuedFraction cf = new ContinuedFraction(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        assertEquals(Arrays.asList(), cf.getCoefficients(0));
        assertEquals(Arrays.asList(1L, 2L), cf.getCoefficients(2));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L), cf.getCoefficients(4));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L), cf.getCoefficients(5));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L), cf.getCoefficients(6));
        
        ContinuedFraction cf2 = new ContinuedFraction(Arrays.asList(1L, 2L, 3L, 4L, 5L), 2);
        assertEquals(Arrays.asList(), cf2.getCoefficients(0));
        assertEquals(Arrays.asList(1L, 2L), cf2.getCoefficients(2));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L), cf2.getCoefficients(5));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L, 3L), cf2.getCoefficients(6));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L, 3L, 4L), cf2.getCoefficients(7));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L, 3L, 4L, 5L), cf2.getCoefficients(8));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L, 3L, 4L, 5L, 3L), cf2.getCoefficients(9));
        assertEquals(Arrays.asList(1L, 2L, 3L, 4L, 5L, 3L, 4L, 5L, 3L, 4L), cf2.getCoefficients(10));
    }
    
    @Test
    public void testGetConvergents() {
        ContinuedFraction cf = new ContinuedFraction(Arrays.asList(3L, 2L, 5L, 1L, 2L));
        List<XYPair> expected = Arrays.asList(
            new XYPair(3, 1),
            new XYPair(7, 2),
            new XYPair(38, 11),
            new XYPair(45, 13),
            new XYPair(128, 37));
        assertEquals(expected, cf.getConvergents(5));
        
        cf = new ContinuedFraction(Arrays.asList(2L, 4L), 1);
        expected = Arrays.asList(
            new XYPair(2, 1),
            new XYPair(9, 4),
            new XYPair(38, 17),
            new XYPair(161, 72),
            new XYPair(682, 305));
        assertEquals(expected, cf.getConvergents(5));
        
        cf = new ContinuedFraction(Arrays.asList(4L, 1L, 2L, 3L), 2);
        expected = Arrays.asList(
            new XYPair(4, 1),
            new XYPair(5, 1),
            new XYPair(14, 3),
            new XYPair(47, 10),
            new XYPair(108, 23));
        assertEquals(expected, cf.getConvergents(5));
    }
    
}
