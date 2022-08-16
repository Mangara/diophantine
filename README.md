# diophantine
Solver for linear and quadratic diophantine equations

## Usage

```java
import com.github.mangara.diophantine.*;
import java.util.Iterator;

// Solve x^2 - 6xy + 9y^2 - 324 = 0
Iterator<XYPair> solutions = QuadraticSolver.solve(1, -6, 9, 0, 0, -324);

System.out.println("The first 10 solutions are:");
for (int i = 0; i < 10; i++) {
    XYPair sol = solutions.next();
    System.out.println(sol + " with x + y = " + (sol.x.add(sol.y)));
}
```

## Current status

Work in progress.

| Case             | Status        |
| -------------    | ------------- |
| Linear           | Completed     |
| Quadratic, D = 0 | Completed     |
| Quadratic, D > 0 | None          |
| Quadratic, D < 0 | None          |

## Principles

- If this library can solve your equation, you get an iterator over *all* solutions.
- If this library cannot solve your equation, it throws an exception rather than returning incorrect results.
