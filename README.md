# Distributed-Computing: Topology Management In Peer-to-Peer Systems (also known as Jelasity and Babaoglu’s algorithm) 

This project consists of three java files which needs to be present in a single folder:
1) TMAN.java:- The main java class which performs the initialisation and execution phase 
2) distgraph.java:- The java class which is called by TMAN class to plot the sum of distances graph for each cycle
3) finalg.java:-Another java class called by TMAN class to plot the connecetion of all the nodes with their neighbouring nodes after the end of evolution phase

Since JFreechart and JCommon libraries are used for plotting the various graphs, the jfreechart-1.0.19.jar and jcommon-1.0.23.jar have also been provided in the same folder.

A makefile has also been provided to execute all the codes.

Instructions to run:
1) Type "make" on the command prompt in the folder where all the files are present
2) Type the following command:
                      java -cp ".:jfreechart-1.0.19.jar:jcommon-1.0.23.jar" TMAN <Total no of nodes> <no. of neighbors> <topology>
   ex: for N:1000,k=30,topology:B, type
                      java -cp ".:jfreechart-1.0.19.jar:jcommon-1.0.23.jar" TMAN 1000 30 B
3) The ouput will consist of sum of distances of all nodes after each cycle, a graph showing the sum of distances after each cycle and a Java applet that shows the final connection of nodes and their neighboring nodes
4) All the required files(List of neighboring nodes after 1,5,10,15 cycles; graph showing connection of nodes and their neighboring nodes after 1,5,10,15 cycles; and a file having sum of distances after each cycle in a single file) will also get created in the same folder in which all the source code files are present.   


Other Information:
i) Operating System: Linux/Windows
ii) Language written: java
iii) Running environment: jdk
iv) Compiler used: Eclipse IDE(for Windows)
