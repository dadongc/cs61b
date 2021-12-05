public class LinkedListDeque<T> {
    private int size;
    private Node head;
    private Node tail;
    private class Node {
        public T item;
        public Node next;
        public Node prev;
        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
        public Node() {
            item = null;
            next = null;
            prev = null;
        }
        public T getItem(int index) {
            if (index == 0) {
                return item;
            }
            return this.next.getItem(index-1);
        }
    }

    public LinkedListDeque() {
        size = 0;
        tail = head = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, head.next, head);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, tail, tail.prev);
        tail.prev.next = newNode;
        tail.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node tmp = head;
        for (int i = 0;i < size;i++) {
            System.out.println(tmp.next.item);
            tmp = tmp.next;
        };
    }

    public T removeFirst() {
        if (size == 0) {
            return head.item;
        }
        Node tmp = head.next;
        tmp.next.prev = head;
        head.next = tmp.next;
        size--;
        return tmp.item;
    }

    public T removeLast() {
        if (size == 0) {
            return tail.item;
        }
        Node tmp = tail.prev;
        tmp.prev.next = tail;
        tail.prev = tmp.prev;
        size--;
        return tmp.item;
    }

    public T get(int index) {
        if (size <= index) {
            return head.item;
        }
        Node tmp = head.next;
        for(int i = 0;i < index;i++) {
            tmp = tmp.next;
        }
        return tmp.item;
    }

    public T getRecursive(int index) {
        if (size <= index) {
            return head.item;
        }

        return head.next.getItem(index);
    }

}