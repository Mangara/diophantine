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
package com.github.mangara.diophantine.quadratic;

import com.github.mangara.diophantine.XYPair;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReductionTest {
    
    public ReductionTest() {
    }

    @Test
    public void testForEquation() {
        System.out.println("forEquation");
        
        RestrictedEquation eq = new RestrictedEquation(7, -1, -4, -580594);
        Reduction reduction = Reduction.forEquation(eq);
        
        RestrictedEquation reduced = reduction.reduce(eq);
        assertEquals("-983750013509 x^2 + -327920624737 xy + -27327048196 y^2 = 580594, with D = 113", reduced.toString());
    }
    
    @Test
    public void testReduced() {
        System.out.println("reduced");
        
        RestrictedEquation eq = new RestrictedEquation(7, -1, -4, -580594);
        Reduction reduction = new Reduction(BigInteger.valueOf(82943), BigInteger.valueOf(13824), BigInteger.valueOf(497652), BigInteger.valueOf(82943));
        
        RestrictedEquation reduced = reduction.reduce(eq);
        assertEquals("-983750013509 x^2 + -327920624737 xy + -27327048196 y^2 = 580594, with D = 113", reduced.toString());
    }

    @Test
    public void testUnreduce() {
        System.out.println("unreduce");
        
        List<XYPair> reducedSolutions = Arrays.asList(new XYPair(-14098152293L, 84587893911L), new XYPair(-1413994162L, 8483862685L), new XYPair(-2631814898L, 15790699005L), new XYPair(-48120635L, 288720329L), new XYPair(-1819538693L, 10917100535L), new XYPair(-35624078L, 213741891L), new XYPair(-410527342L, 2463134355L), new XYPair(-29983835L, 179900841L));
        Reduction reduction = new Reduction(BigInteger.valueOf(82943), BigInteger.valueOf(13824), BigInteger.valueOf(497652), BigInteger.valueOf(82943));
        
        List<XYPair> expected = Arrays.asList(new XYPair(-212635, -255963), new XYPair(-21326, -25669), new XYPair(-39694, -47781), new XYPair(-709, -773), new XYPair(-17659, 25669), new XYPair(-370, 357), new XYPair(-3986, 5781), new XYPair(-421, -357));
        List<XYPair> result = reduction.unreduce(reducedSolutions);
        
        assertEquals(expected, result);
    }
    
}
