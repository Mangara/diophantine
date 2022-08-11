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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sander Verdonschot <sander.verdonschot at gmail.com>
 */
public class MergedIteratorTest {
    
    public MergedIteratorTest() {
    }

    @Test
    public void testRoundRobin() {
        System.out.println("returns alternating results for two iterators");
        Iterator<Integer> even = Arrays.asList(0, 2, 4, 6, 8).iterator();
        Iterator<Integer> odd = Arrays.asList(1, 3, 5, 7, 9).iterator();
        Iterator<Integer> it = MergedIterator.merge(Arrays.asList(even, odd));
        
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), take(it, 10));
    }
    
    @Test
    public void testRoundRobinMore() {
        System.out.println("returns alternating results for four iterators");
        Iterator<Integer> even = Arrays.asList(0, 2).iterator();
        Iterator<Integer> odd = Arrays.asList(1, 3).iterator();
        Iterator<Integer> negEven = Arrays.asList(-2, -4).iterator();
        Iterator<Integer> negOdd = Arrays.asList(-1, -3).iterator();
        Iterator<Integer> it = MergedIterator.merge(Arrays.asList(even, odd, negEven, negOdd));
        
        assertEquals(Arrays.asList(0, 1, -2, -1, 2, 3, -4, -3), take(it, 8));
    }
    
    @Test
    public void testShortAndLong() {
        System.out.println("returns results from one iterator after others are exhausted");
        Iterator<Integer> even = Arrays.asList(0).iterator();
        Iterator<Integer> odd = Arrays.asList(1, 3, 5).iterator();
        Iterator<Integer> it = MergedIterator.merge(Arrays.asList(even, odd));
        
        assertEquals(Arrays.asList(0, 1, 3, 5), take(it, 4));
    }
    
    @Test
    public void testShortAndLongHasNext() {
        System.out.println("hasNext returns true if one iterator still has elements");
        Iterator<Integer> even = Arrays.asList(0).iterator();
        Iterator<Integer> odd = Arrays.asList(1, 3, 5).iterator();
        Iterator<Integer> it = MergedIterator.merge(Arrays.asList(even, odd));
        
        assertEquals(Arrays.asList(0, 1), take(it, 2));
        assertTrue(it.hasNext());
        assertEquals(3, it.next());
        assertTrue(it.hasNext());
    }
    
    @Test
    public void testExhausted() {
        System.out.println("throws if next is called when all iterators are exhausted");
        Iterator<Integer> even = Arrays.asList(0).iterator();
        Iterator<Integer> odd = Arrays.asList(1).iterator();
        Iterator<Integer> it = MergedIterator.merge(Arrays.asList(even, odd));
        
        assertEquals(Arrays.asList(0, 1), take(it, 2));
        assertThrows(NoSuchElementException.class, () -> { it.next(); });
    }
    
    @Test
    public void testExhaustedHasNext() {
        System.out.println("hasNext returns false when all iterators are exhausted");
        Iterator<Integer> even = Arrays.asList(0).iterator();
        Iterator<Integer> odd = Arrays.asList(1).iterator();
        Iterator<Integer> it = MergedIterator.merge(Arrays.asList(even, odd));
        
        assertEquals(Arrays.asList(0, 1), take(it, 2));
        assertFalse(it.hasNext());
    }

    private List<Integer> take(Iterator<Integer> it, int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(it.next());
        }
        return result;
    }
    
}
