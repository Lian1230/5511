class Node {
  protected int data;
  protected Node link;

  /*  Constructor  */
  public Node(int d, Node n) {
    data = d;
    link = n;
  }

  /*  Function to set link to next Node  */
  public void setLink(Node n) {
    link = n;
  }

  /*  Function to set data to current Node  */
  public void setData(int d) {
    data = d;
  }

  /*  Function to get link to next node  */
  public Node getLink() {
    return link;
  }

  /*  Function to get data from current Node  */
  public int getData() {
    return data;
  }
}

/* Class singlyLinkedList */
class singlyLinkedList {
  protected Node start; // the first Node
  protected Node end; // the last Node 
  public int size; // the number of Node in the list

  /*  Constructor  */
  public singlyLinkedList() {
    start = null;
    end = null;
    size = 0;
  }

  /*  Function to check if list is empty  */
  public boolean isEmpty() {
    return start == null;
  }

   /* the first way to get size -- just render the size, since size get increase at inserting action.*/
   public int getSize() {
    return size;
  }

  /* Second way to get size -- loop the list*/
  public int countSize() {
    if (start == null)
      return 0;
    return recursiveCount(start, 1);
  }

  /* Function to recursively check the next node */
  protected int recursiveCount(Node node, int count) {
    Node nextNode = node.getLink();
    if (nextNode == null)
      return count;
    count++;
    return recursiveCount(nextNode, count);
  }

  /*  Function to insert an element at begining  */
  public void insertAtStart(int val) {
    Node newNode = new Node(val, null);
    size++;
    if (start == null) {
      start = newNode;
      end = start;
    } else {
      newNode.setLink(start);
      start = newNode;
    }
  }

  /*  Function to insert an element at end  */
  public void insertAtEnd(int val) {
    Node newNode = new Node(val, null);
    size++;
    if (start == null) {
      start = newNode;
      end = start;
    } else {
      end.setLink(newNode);
      end = newNode;
    }
  }

  /*  Function to print elements  */
  public void print() {
    System.out.print("Singly Linked List: ");
    if (size == 0) {
      System.out.print("empty\n");
      return;
    }
    if (start.getLink() == null) {
      System.out.println(start.getData());
      return;
    }
    Node nextNode;
    System.out.print(start.getData() + " -> ");
    nextNode = start.getLink();
    while (nextNode.getLink() != null) {
      System.out.print(nextNode.getData() + " -> ");
      nextNode = nextNode.getLink();
    }
    System.out.print(nextNode.getData() + "\n");
  }

  // Main method to reverse the link, entrance to the following recursive method 
  public void reverseList() {
    if (start != null) {
      end = start;
      Node nextNode = start.getLink();
      recursiveReverseList(null, start, nextNode);
    }
  }
  // recursively check every Node in the list
  private void recursiveReverseList(Node previousNode, Node currentNode, Node nextNode) {
    currentNode.setLink(previousNode);
    if (nextNode == null)
      start = currentNode;
    else {
      Node nextnextNode = nextNode.getLink();
      recursiveReverseList(currentNode, nextNode, nextnextNode);
    }
  }
}

public class Q5 {
  // generate a ascending singly linked list filled with number of 1-10;
  static singlyLinkedList generateAscendingList(int size) {
    singlyLinkedList list = new singlyLinkedList();
    int largestNum = 0;
    for (int i = 0; i < size; i++) {
      largestNum += Math.random() * 10 + 1;
      list.insertAtEnd(largestNum);
    }
    return list;
  }

  public static void main(String[] args) {
    singlyLinkedList list = generateAscendingList(10);
    list.print();
    list.reverseList();
    System.out.print("\nReversed ");
    list.print();
  }
}