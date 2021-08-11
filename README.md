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
The maze is represented as a two-dimensional array of binary int - 1 symbolizes a wall while 0 symbolizes a passage. We chose Prim's algorithm to generate interesting mazes with a lot of dead ends. The two-dimensional array is initialized with every cell being a wall, then after choosing a random starting cell from the frame of the grid, the algorithm chooses a close by cell and "clears" itself a passage to it. This step is repeated until there are no possible cell to choose from. If every cell is a node in a graph, then the algorithm will find a minimum spanning tree in it. After the maze is created, a random cell on the other side of the frame of the grid is chosen as the goal cell.

# How do you solve a maze???
Maze solving is much like graph traversing. We implemented 3 different ways of finding the route from start to goal:
* DFS - Depth First Search
* BFS - Breadth First Search
* Best First Search - Variation of BFS  algorithm that chooses the next cell to go to by calculating the cheapest path (a diagonal step costs more than a regular step)

# Compress & Decompress
To be able to stream the data we needed to compress and decompress it. First and foremost, the two-dimensional array is flatten out to a single-dimensional array of bytes (much cheaper in memory). All the basic information about the maze like its size and start/goal cell is converted and compressed to 12 bytes.

We implemented 2 different methods for compressing and decompressing:

* Simple Compressor - Saves the number of consecutive appeareances of each value (0 or 1) starting from 0. 
For example a maze whose values are (flatten out): 1,1,1,0,0,1,0,0,0,0,0,0,1,1 will be compressed to - 0,3,2,1,6,2

# Servers & Threads
