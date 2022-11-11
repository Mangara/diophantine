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
package io.github.mangara.diophantine.utils;

import io.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
    
    @Test
    public void testConvergent() {
        ContinuedFraction cf = new ContinuedFraction(Arrays.asList(3L, 2L, 5L, 1L, 2L));
        assertEquals(new XYPair(BigInteger.valueOf(3), BigInteger.valueOf(1)), cf.convergent(0));
        assertEquals(new XYPair(BigInteger.valueOf(7), BigInteger.valueOf(2)), cf.convergent(1));
        assertEquals(new XYPair(BigInteger.valueOf(38), BigInteger.valueOf(11)), cf.convergent(2));
        assertEquals(new XYPair(BigInteger.valueOf(45), BigInteger.valueOf(13)), cf.convergent(3));
        assertEquals(new XYPair(BigInteger.valueOf(128), BigInteger.valueOf(37)), cf.convergent(4));
        
        cf = new ContinuedFraction(Arrays.asList(2L, 4L), 1);
        assertEquals(new XYPair(BigInteger.valueOf(2), BigInteger.valueOf(1)), cf.convergent(0));
        assertEquals(new XYPair(BigInteger.valueOf(9), BigInteger.valueOf(4)), cf.convergent(1));
        assertEquals(new XYPair(BigInteger.valueOf(38), BigInteger.valueOf(17)), cf.convergent(2));
        assertEquals(new XYPair(BigInteger.valueOf(161), BigInteger.valueOf(72)), cf.convergent(3));
        assertEquals(new XYPair(BigInteger.valueOf(682), BigInteger.valueOf(305)), cf.convergent(4));
        
        cf = new ContinuedFraction(Arrays.asList(4L, 1L, 2L, 3L), 2);
        assertEquals(new XYPair(BigInteger.valueOf(4), BigInteger.valueOf(1)), cf.convergent(0));
        assertEquals(new XYPair(BigInteger.valueOf(5), BigInteger.valueOf(1)), cf.convergent(1));
        assertEquals(new XYPair(BigInteger.valueOf(14), BigInteger.valueOf(3)), cf.convergent(2));
        assertEquals(new XYPair(BigInteger.valueOf(47), BigInteger.valueOf(10)), cf.convergent(3));
        assertEquals(new XYPair(BigInteger.valueOf(108), BigInteger.valueOf(23)), cf.convergent(4));   
    }
    
    @Test
    public void testGetCompleteQuotientDenominators() {
        long a = -157, b = 79, c = 1170;
        List<BigInteger> expected = Arrays.asList(1170L, -877L, 21L, -1L, 2L, 15L, 1L, 15L, 2L, 15L, 1L, 15L, 2L)
                .stream().map(x -> BigInteger.valueOf(x)).collect(Collectors.toList());
        ContinuedFraction cf = ContinuedFraction.ofExpression(a, b, c);
        assertEquals(expected, cf.getCompleteQuotientDenominators(expected.size(), BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c)));
                
        a = 6; b = 7; c = 3;
        expected = Arrays.asList(9L, 7L, 2L, 7L)
                .stream().map(x -> BigInteger.valueOf(x)).collect(Collectors.toList());
        cf = ContinuedFraction.ofExpression(a, b, c);
        assertEquals(expected, cf.getCompleteQuotientDenominators(expected.size(), BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c)));

        a = 1; b = 35; c = 2;
        expected = Arrays.asList(2L, 5L, 2L, 5L, 2L)
                .stream().map(x -> BigInteger.valueOf(x)).collect(Collectors.toList());
        cf = ContinuedFraction.ofExpression(a, b, c);
        assertEquals(expected, cf.getCompleteQuotientDenominators(expected.size(), BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c)));
        
        a = -237537497102073097L; b = 124; c = 1066139978779423449L;
        expected = Arrays.asList(1066139978779423449L, -643988675321885220L, 52923690746607965L, -12619053908566113L, 28969918307469L, -21969291193273L, 483344781480L, -266009330257L, 32209156781L, -24593368497L, 512857960L, -438673113L, 2896269L, -272913L, 18120L, -14061L, 257L, -40L, 9L, 11L, 8L, 3L, 1L, 3L, 8L, 11L, 9L, 12L, 5L, 15L, 4L, 15L, 5L, 12L, 9L, 11L)
                .stream().map(x -> BigInteger.valueOf(x)).collect(Collectors.toList());
        cf = ContinuedFraction.ofExpression(a, b, c);
        assertEquals(expected, cf.getCompleteQuotientDenominators(expected.size(), BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c)));
        
        a = 29257053322382397L; b = 113; c = -1142318710686488692L;
        expected = Arrays.asList(-1142318710686488692L, 1084553935345764536L, -749331304040638L, 1464986472124L, -556305457994L, 215767108544L, -79158660952L, 33546080998L, -9642375356L, 7218251756L, -175173226L, 30785648L, -4572494L, 1617484L, -750884L, 164242L, -3136L, 176L, -8L, 14L, 8L, 4L, 16L, 2L, 16L, 4L, 8L, 8L, 4L)
                .stream().map(x -> BigInteger.valueOf(x)).collect(Collectors.toList());
        cf = ContinuedFraction.ofExpression(a, b, c);
        assertEquals(expected, cf.getCompleteQuotientDenominators(expected.size(), BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c)));
    }
    
}
