import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class QuickSort<T> {
  boolean compareMethod(T value, T pivot) throws Exception {
    if (value instanceof String) {
      return ((String) value).compareToIgnoreCase(((String) pivot)) < 0;
    } else if (value instanceof Integer)
      return ((Integer) value) <= ((Integer) pivot);
    else
      throw new Exception("Sorry, unsupported type found. Only take Integer and String");
  }

  /* This function takes last element as pivot,
   places the pivot element at its correct
   position in sorted array, and places all
   smaller (smaller than pivot) to left of
   pivot and all greater elements to right
   of pivot */
  int partition(T arr[], int start, int end) {
    T pivot = arr[end];
    int i = (start - 1); // index of smaller element
    for (int j = start; j < end; j++) {
      // If current element is smaller than or equal to pivot
      try {
        if (compareMethod(arr[j], pivot)) {
          i++;
          // swap arr[i] and arr[j]
          T temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // swap arr[i+1] and arr[end] (or pivot)
    T temp = arr[i + 1];
    arr[i + 1] = arr[end];
    arr[end] = temp;

    return i + 1;
  }

  /* The main function that implements QuickSortGenetic()
    arr[] --> Array to be sorted,
    start  --> Starting index,
    end  --> Ending index */
  void sort(T[] arr, int start, int end) {
    if (start < end) {
      /* pi is partitioning index, arr[pi] is
        now at right place */
      int pi = partition(arr, start, end);

      // Recursively sort elements before partition and after partition
      sort(arr, start, pi - 1);
      sort(arr, pi + 1, end);
    }
  }
}

public class Asg2Question6 {
  
  static class getArray {
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

  static void printArray(String[] arr) {
    int n = arr.length;
    for (int i = 0; i < n; ++i)
      System.out.print(arr[i] + "\n");
    System.out.println();
  }

  public static void main(String[] args) {
    String[] dataArr = getArray.fromTxt("./ds17s-asg2-data.txt");
    int len = dataArr.length;

    QuickSort<String> strSort = new QuickSort<>();
    strSort.sort(dataArr, 0, len - 1);

    System.out.println("sorted string array: ");
    printArray(dataArr);
  }
}