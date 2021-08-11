# ATP-Project-PartA-PartB

Business logic of the Maze game for ATP course in SISE department of BGU University.

# Table of Content
* [General Information](#General-Information)
* [Technologies](#Technologies)
* [How do you build a maze???](#How-do-you-build-a-maze???)
* [How do you solve a maze???](#How-do-you-solve-a-maze???)
* [Compress & Decompress](#Compress-&-Decompress)
* [Servers and Threads](#Servers-&-Threads)

# General Information
First and second part of the project is to train my skills in creating a fully working custom maze generator and solver, and a multi threaded Client-server architecture that can generate and solve mazes.

# Technologies
The project was written using Java JDK-15.

# How do you build a maze???
The maze is represented as a matrix of binary int - 1 symbolizes a wall while 0 symbolizes a passage. I chose Prim's algorithm to generate interesting mazes. The matrix is initialized with every cell being a wall, then after choosing a random starting cell, the algorithm chooses a close by cell and "clears" itself a passage to it. This step is repeated until there are no possible cell to choose from. If every cell is a node in a graph, then the algorithm will find a minimum spanning tree in it.

# How do you solve a maze???

# Compress & Decompress

# Servers & Threads
