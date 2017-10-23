import java.io.*;

public class QuestionSix {

  // some color to make the console look better
  static final String ANSI_RESET = "\u001B[0m";
  static final String ANSI_BLUE = "\u001B[34m";
  static final String ANSI_CYAN = "\033[0;36m";

  /* method to compare two strings, if true, means the first string should be 
  sorted before the second one (in A-Z order) */
  static boolean compareMethod(String value, String pivot) {
    return value.compareToIgnoreCase(pivot) < 0;
  }

  // selection sort class
  static class SelectionSort {
    void sort(String arr[]) {
      int len = arr.length;

      // One by one move boundary of unsorted sub array
      for (int i = 0; i < len - 1; i++) {
        // Find the minimum element in unsorted array
        int min_index = i;
        for (int j = i + 1; j < len; j++)
          if (compareMethod(arr[j], arr[min_index]))
            min_index = j;

        // Swap the found minimum element with the first element
        String temp = arr[min_index];
        arr[min_index] = arr[i];
        arr[i] = temp;
      }
    }
  }

  // quick sort class
  static class QuickSort {
    /* This function takes last element as pivot, places the pivot element at its correct
     position in sorted array, and places all smaller (smaller than pivot) to left of
     pivot and all greater elements to right of pivot */
    int partition(String arr[], int start, int end) {
      String pivot = arr[end];
      int i = (start - 1); // index of smaller element
      for (int j = start; j < end; j++) {
        // If current element is smaller than or equal to pivot
        try {
          if (compareMethod(arr[j], pivot)) {
            i++;
            // swap arr[i] and arr[j]
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      // swap arr[i+1] and arr[end] (or pivot)
      String temp = arr[i + 1];
      arr[i + 1] = arr[end];
      arr[end] = temp;

      return i + 1;
    }

    // the sort method entry point
    void sort(String[] arr){
      sort_main(arr, 0, arr.length -1);
    }

    // the recursive sort method
    void sort_main(String[] arr, int start, int end) {
      if (start < end) {
      /* pi is partitioning index, arr[pi] is
        now at right place */
        int pi = partition(arr, start, end);

        // Recursively sort elements before partition and after partition
        sort_main(arr, start, pi - 1);
        sort_main(arr, pi + 1, end);
      }
    }
  }

  // method to read all names from the txt file and return an array with all names
  static class CreateArray {
    static String dataStr = "";

    static String[] fromTxt(String filename) {
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        for (String line; (line = br.readLine()) != null;) {
          dataStr = dataStr.concat(line.trim() + ";");
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return dataStr.substring(0, dataStr.length() - 1).split(";");
    }
  }

  // method to print out all names in a given array
  static void printArray(String[] arr) {
    int n = arr.length;
    for (int i = 0; i < n; ++i)
      System.out.print(ANSI_CYAN + arr[i] + " " + ANSI_RESET);
    System.out.println();
  }

  // method to write the given string array into txt file at give location
  static void write(String filename, String[] dataArr) throws IOException {
    FileWriter fw = new FileWriter(filename);

    for (int i = 0; i < dataArr.length; i++) {
      fw.write(dataArr[i] + "\n");
    }
    fw.close();
  }

  
  public static void main(String[] args) {
    String[] dataArr = CreateArray.fromTxt("./ds17s-asg2-data.txt");
    // since both quicksort and selectionsort are all in-place sort method,
    // so we'll need two same arrays.
    String[] dataArr_copy = dataArr;

    QuickSort qs = new QuickSort();
    qs.sort(dataArr);

    SelectionSort ss = new SelectionSort();
    ss.sort(dataArr_copy);

    System.out.println(ANSI_BLUE + "Sorted array using QuickSort: " + ANSI_RESET);
    printArray(dataArr);
    System.out.println("\n" + ANSI_BLUE + "Sorted array using SelectionSort: "+ANSI_RESET);
    printArray(dataArr_copy);

    try {
      write("./sorted_result_quicksort.txt", dataArr);
      write("./sorted_result_selectionsort.txt", dataArr_copy);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}