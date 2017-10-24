Question: 
  Imagine a singly linked list of integers that are sorted into ascending order. The head pointer points to the first node, which contains the smallest integer. Using List ADT, write a java program that revises the list so that its data are sorted into descending order. The head pointer points to the first node, which contains the largest integer.

Intro:
  An in-place reverse method with O(n) complexity. 

Methodology:
  check every node in the singly linked list and link the next node to the previous one

Methods and Classes:
  Class Node: the class to define a Node
  Class singlyLinkedList: the class to contruct a singly linked list
  Method generateAscendingList: Generate a random ascending singly linked list.
  Main: Implemention of the reversion
Files relatived:
  Q5.java                   --- the java file with all codes
  Q5-experiments.pdf        --- the console result of 10 times of experiments