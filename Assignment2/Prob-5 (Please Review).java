import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Mohamed Rashed on 10/22/2017.
 */
public class ReversedLinkedList {

    // This segment will declare the original list with 4 arbitrary elements in ascending order
    public static void main(String[] args) {
        LinkedList<Integer> MyLinkedList = new LinkedList<Integer>();
        MyLinkedList.add(1);
        MyLinkedList.add(3);
        MyLinkedList.add(7);
        MyLinkedList.add(9);

        System.out.println("The Original List:");
        printList(MyLinkedList);

        Collections.reverse(MyLinkedList);

        System.out.println("The Reversed List:");
        printList(MyLinkedList);

}
    // This method will print out the given list
    private static void printList(LinkedList<Integer> MyLinkedList){
        Iterator<Integer> i = MyLinkedList.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }
}
