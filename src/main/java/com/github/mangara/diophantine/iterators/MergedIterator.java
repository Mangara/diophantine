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

import com.github.mangara.diophantine.iterators.EmptyIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MergedIterator<T> implements Iterator<T> {

    private final List<Iterator<T>> iterators;
    private int nextIterator = 0;

    @SafeVarargs
    public static <E> Iterator<E> merge(Iterator<E>... iterators) {
        return merge(Arrays.asList(iterators));
    }
    
    public static <E> Iterator<E> merge(Collection<? extends Iterator<E>> iterators) {
        List<Iterator<E>> nonEmptyIterators = iterators.stream()
                .filter((it) -> it.hasNext())
                .collect(Collectors.toList());

        return switch (nonEmptyIterators.size()) {
            case 0 -> new EmptyIterator<>();
            case 1 -> nonEmptyIterators.get(0);
            default -> new MergedIterator<>(nonEmptyIterators);
        };
    }

    private MergedIterator(Collection<? extends Iterator<T>> iterators) {
        this.iterators = new ArrayList<>(iterators);
    }

    @Override
    public T next() {
        for (int i = 1; i < iterators.size(); i++) {
            if (!iterators.get(nextIterator).hasNext()) {
                nextIterator = (nextIterator + 1) % iterators.size();
            }
        }

        T result = iterators.get(nextIterator).next();
        nextIterator = (nextIterator + 1) % iterators.size();
        return result;
    }

    @Override
    public boolean hasNext() {
        for (int i = 0; i < iterators.size(); i++) {
            if (iterators.get(i).hasNext()) {
                return true;
            }
        }
        
        return false;
    }
}
