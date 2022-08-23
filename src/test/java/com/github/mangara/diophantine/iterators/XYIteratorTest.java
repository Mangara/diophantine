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
package com.github.mangara.diophantine.iterators;

import com.github.mangara.diophantine.XYPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XYIteratorTest {
    
    public XYIteratorTest() {
    }

    @Test
    public void testSequence() {
        System.out.println("Sequence");
        
        assertEquals(Arrays.asList(new XYPair(0, 0), new XYPair(1, 0)), take(new XYIterator(), 2));
        
        assertEquals(Arrays.asList(
                new XYPair(0, 0), 
                new XYPair(1, 0), new XYPair(1, -1), new XYPair(0, -1),
                new XYPair(-1, -1), new XYPair(-1, 0), new XYPair(-1, 1), 
                new XYPair(0, 1), new XYPair(1, 1), 
                new XYPair(2, 0)
        ), take(new XYIterator(), 10));
        
        assertEquals(Arrays.asList(
                new XYPair(0, 0), 
                new XYPair(1, 0), new XYPair(1, -1), new XYPair(0, -1),
                new XYPair(-1, -1), new XYPair(-1, 0), new XYPair(-1, 1), 
                new XYPair(0, 1), new XYPair(1, 1), 
                new XYPair(2, 0), new XYPair(2, -1), new XYPair(2, -2), 
                new XYPair(1, -2), new XYPair(0, -2), new XYPair(-1, -2), new XYPair(-2, -2), 
                new XYPair(-2, -1), new XYPair(-2, 0), new XYPair(-2, 1), new XYPair(-2, 2), 
                new XYPair(-1, 2), new XYPair(0, 2), new XYPair(1, 2), new XYPair(2, 2), 
                new XYPair(2, 1), 
                new XYPair(3, 0), new XYPair(3, -1)
        ), take(new XYIterator(), 27));
    }
    
    private <E> List<E> take(Iterator<E> it, int n) {
        List<E> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(it.next());
        }
        return result;
    }
}
