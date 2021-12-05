
import java.util.Arrays;

public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        if (position == 0 || first == null) {
            addFirst(item);
            return;
        }
        IntNode head = first;
        while (--position > 0 && head.next != null) {
            head = head.next;
        }
        head.next = new IntNode(item, head.next);
    }

    // Add another method to the SLList class that reverses the elements. Do this using
    // the existing IntNodes (you should not use new).
    public void iterativeReverse() {
        IntNode prev = null;
        IntNode curr = first;
        while (curr != null) {
            IntNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        first = prev;
    }

    public IntNode recursionReverseHelper(IntNode item) {
        if (item == null || item.next == null) {
            return item;
        }
        IntNode reverse = recursionReverseHelper(item.next);
        item.next.next = item;
        item.next = null;
        return reverse;
    }

    // Extra: If you wrote reverse iteratively, write a second version that uses recursion
    //(you may need a helper method). If you wrote it recursively, write it iteratively.
    public void recursionReverse() {
        first = recursionReverseHelper(first);
    }

    public void Test() {
        addFirst(1);
        addFirst(2);
        addFirst(3);
        iterativeReverse();
        IntNode tmp = first;
        while (tmp != null) {
            System.out.println(tmp.item);
            tmp = tmp.next;
        }
    }

    public static int[] insert(int[] arr, int item, int position) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        if (position >= arr.length) {
            arr[arr.length - 1] = item;
            return arr;
        }
        System.arraycopy(arr, position, arr, position + 1, arr.length - position);
        arr[position] = item;
        return arr;
    }

    public static void reverse(int[] arr) {
        for (int i = 0;i < arr.length / 2;i++) {
            arr[i] ^= arr[arr.length - i - 1];
            arr[arr.length - i - 1] ^= arr[i];
            arr[i] ^= arr[arr.length - i - 1];
        }
    }

    public static int[] replicate(int[] arr) {
        int total = 0;
        for (int a:arr) {
            if (a > 0) {
                total += a;
            }
        }
        int[] arr2 = new int[total];
        int tmp = 0;
        for (int k : arr) {
            for (int j = 0; j < k; j++) {
                arr2[tmp] = k;
                tmp++;
            }
        }
        return arr2;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        int[] arr2 = replicate(arr);
        System.out.println(Arrays.toString(arr2));
    }



}

