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
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContinuedFractionTest {
    
    public ContinuedFractionTest() {
    }

    @Test
    public void testOfFraction() {
        List<Long> expected = Arrays.asList(1L, 1L, 1L, 3L);
        assertEquals(expected, ContinuedFraction.ofFraction(11, 7).getCoefficients());

        expected = Arrays.asList(8L);
        assertEquals(expected, ContinuedFraction.ofFraction(16, 2).getCoefficients());
    }
    
    @Test
    public void testOfRoot() {
        List<Long> expected = Arrays.asList(4L, 2L, 1L, 3L, 1L, 2L, 8L);
        assertEquals(expected, ContinuedFraction.ofRoot(19).getCoefficients());

        expected = Arrays.asList(4L);
        assertEquals(expected, ContinuedFraction.ofRoot(16).getCoefficients());
    }
    
    @Test
    public void testOfExpression() {
        ContinuedFraction cf = ContinuedFraction.ofExpression(0, 19, 1);
        assertEquals(1, cf.getRepetitionStart());
        assertEquals(Arrays.asList(4L, 2L, 1L, 3L, 1L, 2L, 8L), cf.getCoefficients());
        assertEquals(Arrays.asList(2L, 1L, 3L, 1L, 2L, 8L), cf.getRepeatingCoefficients());
        
        cf = ContinuedFraction.ofExpression(5, 4, 7);
        assertEquals(ContinuedFraction.NO_REPETITION, cf.getRepetitionStart());
        assertEquals(Arrays.asList(1L), cf.getCoefficients());
        assertEquals(Collections.EMPTY_LIST, cf.getRepeatingCoefficients());
        
        cf = ContinuedFraction.ofExpression(11, 0, 7);
        assertEquals(ContinuedFraction.NO_REPETITION, cf.getRepetitionStart());
        assertEquals(Arrays.asList(1L, 1L, 1L, 3L), cf.getCoefficients());
        assertEquals(Collections.EMPTY_LIST, cf.getRepeatingCoefficients());
        
        cf = ContinuedFraction.ofExpression(3, 1, 7);
        assertEquals(ContinuedFraction.NO_REPETITION, cf.getRepetitionStart());
        assertEquals(Arrays.asList(0L, 1L, 1L, 3L), cf.getCoefficients());
        assertEquals(Collections.EMPTY_LIST, cf.getRepeatingCoefficients());
        
        cf = ContinuedFraction.ofExpression(6, 7, 3);
        assertEquals(2, cf.getRepetitionStart());
        assertEquals(Arrays.asList(2L, 1L, 7L, 2L), cf.getCoefficients());
        assertEquals(Arrays.asList(7L, 2L), cf.getRepeatingCoefficients());
        
        cf = ContinuedFraction.ofExpression(1, 35, 2);
        assertEquals(1, cf.getRepetitionStart());
        assertEquals(Arrays.asList(3L, 2L, 5L), cf.getCoefficients());
        assertEquals(Arrays.asList(2L, 5L), cf.getRepeatingCoefficients());
        
        cf = ContinuedFraction.ofExpression(0, 5, 3);
        assertEquals(2, cf.getRepetitionStart());
        assertEquals(Arrays.asList(0L, 1L, 2L, 1L, 12L, 1L, 2L, 2L), cf.getCoefficients());
        assertEquals(Arrays.asList(2L, 1L, 12L, 1L, 2L, 2L), cf.getRepeatingCoefficients());
        
        cf = ContinuedFraction.ofExpression(-237537497102073097L, 124, 1066139978779423449L);
        assertEquals(19, cf.getRepetitionStart());
        assertEquals(Arrays.asList(-1L, 1L, 3L, 2L, 20L, 1L, 6L, 1L, 2L, 1L, 6L, 1L, 12L, 3L, 3L, 1L, 7L, 2L, 3L, 1L, 2L, 7L, 22L, 7L, 2L, 1L, 1L, 1L, 3L, 1L, 4L, 1L, 3L, 1L, 1L), cf.getCoefficients());
        assertEquals(Arrays.asList(1L, 2L, 7L, 22L, 7L, 2L, 1L, 1L, 1L, 3L, 1L, 4L, 1L, 3L, 1L, 1L), cf.getRepeatingCoefficients());

        cf = ContinuedFraction.ofExpression(29257053322382397L, 113, -1142318710686488692L);
        assertEquals(21, cf.getRepetitionStart());
        assertEquals(Arrays.asList(-1L, 1L, 38L, 22L, 1L, 1L, 1L, 1L, 1L, 1L, 6L, 2L, 2L, 1L, 1L, 2L, 7L, 4L, 3L, 1L, 1L, 4L, 1L, 9L, 1L, 4L, 2L, 2L), cf.getCoefficients());
        assertEquals(Arrays.asList(4L, 1L, 9L, 1L, 4L, 2L, 2L), cf.getRepeatingCoefficients());
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
