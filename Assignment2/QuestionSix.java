import java.io.*;

public class QuestionSix {

    // some color to make the console look better
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_CYAN = "\u001B[36m";

    static String[] sortedQs;
    static String[] sortedSs;


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
            sortedSs = arr;
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
        void sort(String[] arr) {
            sort_main(arr, 0, arr.length - 1);
            sortedQs = arr;
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
                for (String line; (line = br.readLine()) != null; ) {
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
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    static void printResult(long[] result1, long[] result2) {
        long sum1 = 0;
        long sum2 = 0;
        System.out.print(ANSI_CYAN + "time\tQuickSort\tSelectionSort\n" + ANSI_RESET);
        int n = result1.length;
        for (int i = 0; i < n; ++i) {
            sum1 += result1[i];
            sum2 += result2[i];
            System.out.print((i + 1) + "\t\t" + result1[i] + "ms" + "\t\t\t" + result2[i] + "ms" + "\n");
        }
        System.out.println(ANSI_CYAN + "average\t" + (sum1 / n) + "ms\t\t" + (sum2 / n) + "ms" + ANSI_RESET);
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

        QuickSort qs = new QuickSort();
        SelectionSort ss = new SelectionSort();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long startTimeQs = System.currentTimeMillis();
        qs.sort(dataArr.clone());
        long endTimeQs = System.currentTimeMillis();
        long elaspedQs = endTimeQs - startTimeQs;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long startTimeSs = System.currentTimeMillis();
        ss.sort(dataArr.clone());
        long endTimeSs = System.currentTimeMillis();
        long elaspedSs = endTimeSs - startTimeSs;

        System.out.println(ANSI_BLUE + "Sorted list using QuickSort:" + ANSI_RESET);
        printArray(sortedQs);
        System.out.println("\n" + ANSI_BLUE + "Sorted list using SelectionSort:" + ANSI_RESET);
        printArray(sortedSs);

        System.out.println();
        System.out.println(ANSI_CYAN + "Quicksort running time: " + elaspedQs + "ms" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Selectionsort running time: " + elaspedSs + "ms" + ANSI_RESET);


        try {
            write("./sorted_result_quicksort.txt", sortedQs);
            write("./sorted_result_selectionsort.txt", sortedSs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}