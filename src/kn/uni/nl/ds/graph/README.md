Code illustrating how to implement in java efficient data structures for sparse graphs which are asymptotically optimal in both space (memory) consumption and runtime complexity of typical graph operations


The classes ```DirectedGraph``` and ```UndirectedGraph``` are abstract, defining the operations that are to be supported by a graph data structure and providing non-abstract methods for counting the number of transitive triples and closed triangles, respectively. The classes ```DirectedGraphAS``` and ```UndirectedGraphAS``` implement the respective abstract classes in an adjacency set representation in which the set of neighbors of every node is stored in a ```java.util.HashSet```.

Slides providing an informal introduction to efficient graph data structures can be found here: [http://doi.org/10.5281/zenodo.4172140](http://doi.org/10.5281/zenodo.4172140).

While classes in this package are kept simple to illustrate the implementation of efficient graph representations, there exist free/open source software libraries, among them:
* **Java Universal Network/Graph Framework** [https://jrtom.github.io/jung/](https://jrtom.github.io/jung/)
* **igraph** (R, python, C) [https://igraph.org/](https://igraph.org/)
* R packages **network** and **networkDynamic** (part of the **statnet** package) [https://cran.r-project.org/web/packages/statnet](https://cran.r-project.org/web/packages/statnet)

Further recommended reading: **Cormen et al.: *Introduction to Algorithms***.
