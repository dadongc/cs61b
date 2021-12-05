import java.util.Arrays;

public class ArrayDeque<E> {
    private Object[] items;
    private int size;

    public ArrayDeque() {
        items = new Object[8];
        size = 0;
    }

    private void ensureCapacity(int cap) {
        if (cap > items.length) {
            int newCap = Math.max(items.length * 2, cap);
            items = Arrays.copyOf(items, newCap);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    public void addLst(E item) {
        ensureCapacity(size + 1);
        items[size++] = item;
    }

    public E getLast() {
        return get(size - 1);
    }

    public E get(int index) {
        rangeCheck(index);
        return (E) items[index];
    }

    public int getSize() {
        return size;
    }

    public E remove(int index) {
        rangeCheck(index);
        E item = (E) items[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(items, index + 1, items, index, numMoved);
        }
        items[size--] = null;
        return item;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(items[i]);
        }
    }

    public void addFirst(E item) {
        ensureCapacity(size + 1);
        System.arraycopy(items, 0, items, 1, size++);
        items[0] = item;
    }


    public E removeFirst() {
        rangeCheck(0);
        E item = (E) items[0];
        System.arraycopy(items, 1, items, 0, --size);
        return item;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        System.arraycopy(a, 1, a, 0, 2);
        a[0] = 0;
        System.out.println(Arrays.toString(a));
    }
}
