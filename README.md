# diophantine
A solver for linear and quadratic diophantine equations.

In particular, it finds integer solutions $(x, y)$ to the general quadratic equation $ax^2 + bxy + cy^2 + dx + ey + f = 0$ with integer coefficients $a$, $b$, $c$, $d$, $e$, and $f$.

## Usage

### Add `diophantine` as a dependency

You can download the JAR files directly from [the releases](https://github.com/Mangara/diophantine/releases), or [install from GitHub packages](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package):

1. Create a GitHub [personal access token](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token) with `read:packages` permissions.

1. In your `~/.m2/settings.xml`, add

```xml
  <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
```

where `USERNAME` is your GitHub username and `TOKEN` is the personal access token.

1. In your `pom.xml`, add

```xml
<repositories>
  <repository>
    <id>github</id>
    <name>Diophantine</name>
    <url>https://maven.pkg.github.com/mangara/diophantine</url>
  </repository>
</repositories>
```

and

```xml
<dependencies>
  <dependency>
    <groupId>io.github.mangara</groupId>
    <artifactId>diophantine</artifactId>
    <version>1.0</version>
  </dependency>
</dependencies>
```

1. Run `mvn install`

### Use it in your project

`QuadraticSolver.solve` is the primary entrypoint:

```java
import io.github.mangara.diophantine.QuadraticSolver;
import io.github.mangara.diophantine.XYPair;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Solve 8x^2 - 24xy + 18y^2 + 5x + 7y + 16 = 0
        Iterator<XYPair> solutions = QuadraticSolver.solve(8, -24, 18, 5, 7, 16);

        System.out.println("The first 10 solutions are:");
        for (int i = 0; i < 10; i++) {
            XYPair sol = solutions.next();
            System.out.println(sol + " with x + y = " + (sol.x.add(sol.y)));
        }
    }
}
```

The solutions are not guaranteed to be in any particular order, although later solutions tend to have larger absolute value.

## Principles

- If we can solve an equation, return an iterator over *all* solutions.
- If we cannot solve an equation, throw an exception rather than returning incorrect results.

## Resources and acknowledgements

The algorithms and techniques in this library are based on the academic literature. It owes a large debt to [Dr. Keith Matthews](http://www.numbertheory.org/keith.html), both for distilling the proofs and theorems into accessible algorithms, and for graciously answering my questions. The following papers and websites have been particularly helpful:

- K. R. Matthews, Solving the Diophantine equation $ax^2 + bxy + cy^2 + dx + ey + f = 0$. [(pdf)](http://www.numbertheory.org/PDFS/general_quadratic_solution.pdf)
  *This was my starting point, and contains algorithms for the "easy" cases D = 0, D a perfect square, and k = 0. It also contains the setup for the remaining cases.*

- D. A. Alpern, Methods to solve quadratic Diophantine equations. [(html)](https://www.alpertron.com.ar/METHODS.HTM)
  *This was the basis for the linear solver, and for the first batch of unit tests. Dario's [online solver](https://www.alpertron.com.ar/QUAD.HTM) was also very useful to validate my implementation.*

- K. R. Matthews, Lagrange's algorithm revisited: solving $at^2 + btu + cu^2 = n$ in the case of negative discriminant, Journal of Integer Sequences, Vol. 17 (2014), Article 14.11.1. [(html)](https://cs.uwaterloo.ca/journals/JIS/VOL17/Matthews/matt10.html) [(pdf)](https://cs.uwaterloo.ca/journals/JIS/VOL17/Matthews/matt10.pdf)
  *This paper is the basis for the restricted elliptical solver (D < 0).*

- K. R. Matthews, The Diophantine equation $ax^2 + bxy + cy^2 = N$, $D = b^2 - 4ac > 0$, Journal de Théorie des Nombres de Bordeaux, Tome 14 (2002) no. 1, pp. 257-270. [(html)](http://www.numdam.org/item/JTNB_2002__14_1_257_0/) [(pdf)](http://www.numdam.org/item/JTNB_2002__14_1_257_0.pdf)
  *This paper is the basis for the restricted hyperbolic solver (D > 0).*

- K. R. Matthews and J. P. Robertson, On solving a binary quadratic Diophantine equation, Rocky Mountain J. Math. 51(4): 1369-1385 (August 2021). [(doi)](https://dx.doi.org/10.1216/rmj.2021.51.1369)
  *This paper provides the transformation from the general hyperbolic (D > 0) case to the restricted case.*

- [Project Euler](https://projecteuler.net/).
  *This site has kept my interest in mathematical programming alive for years. I started writing this solver to solve some Project Euler problems, and I'd already developed many of the utility functions for previous problems.*

## Contributing

You can contribute to diophantine through issues and/or pull requests (PRs). In both cases:

- Please search for existing issues and PRs before creating your own.
- This project is maintained in spare time, so it may take a while before your issue or PR gets a response.

### Issues

Issues are a great place to report anything that can be improved, such as reporting a bug, requesting a new feature, or discussing potential changes before a PR is created. If you find an existing issue that addresses the problem you're having, please add your own reproduction information to the existing issue rather than creating a new one. Adding a reaction can also help by indicating that a particular problem is affecting more than just the reporter or that a requested feature is popular.

### Pull requests

PRs are always welcome and can be a quick way to get your fix or improvement implemented. In general, PRs should:

- Only fix/add the functionality in question **OR** address wide-spread whitespace/style issues, not both.
- Add unit or integration tests for fixed or changed functionality.
- Have a narrow scope. It's often better to split large changes into multiple PRs that each address part of the problem.

For changes that address core functionality or would require breaking changes, it's best to open an issue to discuss your proposal first.

In general, development on this repository follows the ["fork-and-pull" Git workflow](https://github.com/susam/gitpr)

1. Fork the repository to your own Github account
2. Clone the project to your machine
3. Create a branch locally with a succinct but descriptive name
4. Commit changes to the branch
5. Push changes to your fork
6. Open a PR in this repository

## License

Copyright 2022 Sander Verdonschot ([@Mangara](https://github.com/Mangara))

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
