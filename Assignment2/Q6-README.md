Question: 
  Write a programs to compare sorting time using (a) selection sort and (b) quick sort to sort a list of records containing names. The file ds17s-asg2- data.txt contains some data to use for the sorting. Measure the time needed to perform the sort in both cases.

Methodology:
  Use String.compareToIgnoreCase method to compare two name. No character set need to be designated.

Methods and Classes:
  compareMethod: the method that wrap String.compareToIgnoreCase

  SelectionSort: the class to sort an array of string by selection method

  QuickSort: the class to sort an array of string by quicksort method

  CreateArray: the class that read names from the .txt file then convert it into an array.

  write: the method to write names in an array into .txt file.

  Main method: Implement all classed and mehtods

Files relatived:
  Q6.java                        --- the java file with all codes
  Q6-result-of-comparison.pdf    --- the result of 10 times of experiments
  Q6-sorted_result_quicksort.txt --- the sorted result of 'ds17s-asg2-data.txt' using quicksort
  Q6-sorted_result_quicksort.txt --- the sorted result of 'ds17s-asg2-data.txt' using selection sort