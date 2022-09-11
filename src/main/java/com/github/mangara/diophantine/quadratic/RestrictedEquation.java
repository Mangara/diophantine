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

import com.github.mangara.diophantine.Utils;
import java.math.BigInteger;

/**
 * Represents the equation a x^2 + b xy + c y^2 + f = 0.
 */
public class RestrictedEquation {

    public static final RestrictedEquation NO_SOLUTIONS = new RestrictedEquation(BigInteger.TWO, BigInteger.TWO, BigInteger.TWO, BigInteger.ONE);
    
    public final BigInteger a, b, c, f, absF, D;
    
    public RestrictedEquation(long a, long b, long c, long f) {
        this(BigInteger.valueOf(a), BigInteger.valueOf(b), BigInteger.valueOf(c), BigInteger.valueOf(f));
    }
    
    public RestrictedEquation(BigInteger a, BigInteger b, BigInteger c, BigInteger f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.f = f;

        this.absF = f.abs();
        this.D = Utils.discriminant(a, b, c);
    }
    
    /**
     * Returns an equation that represents the same set of solutions as this one,
     * but with gcd(a, b, c) = 1, or NO_SOLUTIONS if gcd(a, b, c) does not divide f.
     * @return 
     */
    public RestrictedEquation withoutCommonDivisor() {
        BigInteger gcd = a.gcd(b).gcd(c);
        
        if (gcd.equals(BigInteger.ONE)) {
            return this;
        } else if (f.remainder(gcd).signum() == 0) {
            return new RestrictedEquation(a.divide(gcd), b.divide(gcd), c.divide(gcd), f.divide(gcd));
        } else {
            return NO_SOLUTIONS;
        }
    }
}
