import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

/**
 * A HeapArray holds element on a priority heap, which orders the elements
 * according to their natural order.
 * <p>
 * The heap data structure maintains the invariant that in its underlying tree
 * structure the parent is less than all of its children. This implementation
 * maps the tree structure into an array which maintains the heap invariant such
 * that array[n] <= array[2*n] and array[n] <= array[2*n+1] for all elements in
 * the array.
 *
 * @param <E>
 *            the type of the objects stored in the array (must implement
 *            Comparable)
 */
public class HeapArray<E extends Comparable<E>> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 11;

    private static final int DEFAULT_CAPACITY_RATIO = 2;

    private int size;

    private E[] elements;

    /**
     * Constructs a heap array with an initial capacity of 11.
     */
    public HeapArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a heap array with the specified capacity.
     *
     * @param initialCapacity
     *            the desired initial capacity.
     */
    public HeapArray(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException();
        }

        elements = newElementArray(initialCapacity);
    }

    /**
     * Remove all elements from the heap array
     */
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    /**
     * Adds the specified object to the heap array.
     *
     * @param o
     *            - the object to be added
     */
    public void add(E o) {
        if (null == o) {
            throw new NullPointerException();
        }
        int i = size;
        if (i >= elements.length)
            growToSize(i + 1);
        size = i + 1;
        if (i == 0)
            elements[0] = o;
        else
            siftUp(i, o);
    }

    /**
     * Remove the smallest item from the heap array.
     *
     * @return - the smallest item in the heap array.
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E result = elements[0];
        removeAt(0);
        return result;
    }

    /**
     * Get the element stored at the specified index in the heap array.
     *
     * @param index
     *            the index of the element.
     * @return the element stored at the specified index.
     */
    public E get(int index) {
        return elements[index];
    }

    /**
     * Peek at the smallest item in the heap array (but do not remove it).
     *
     * @return the smallest item in the heap array.
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elements[0];
    }

    /**
     * Remove the specified object from the heap array.
     *
     * @param o
     *            the object to be removed.
     * @return whether or not the request object was found and removed.
     */
    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether or not the heap array contains the specified object.
     *
     * @param object
     *            the object to be checked.
     * @return whether or not the specified object is in the heap array.
     */
    public boolean contains(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the heap array is empty.
     *
     * @return whether or not the heap array is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the heap array (how many items are in it).
     *
     * @return the size of the heap array.
     */
    public int size() {
        return size;
    }

    /**
     * Returns a new array containing all the elements contained in the heap
     * array.
     *
     * @return the new array containing all the elements contained in the heap
     *         array
     */
    public Comparable<E>[] toArray() {
        return Arrays.copyOf(elements, elements.length);
    }

    /**
     * Returns an array containing all the elements contained in the heap array.
     * If the specified array is large enough to hold the elements, the
     * specified array is used, otherwise an array of the same type is created.
     * If the specified array is used and is larger than the heap array, the
     * array element following the heap array contents is set to null.
     *
     * @param <T>
     *            the array element type used for the return array.
     * @param destinationArray
     *            the array used as specified above.
     * @return the new array containing the elements contained in the heap
     *         array.
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] destinationArray) {
        if (size > destinationArray.length) {
            Class<?> ct = destinationArray.getClass().getComponentType();
            destinationArray = (T[]) Array.newInstance(ct, size);
        }
        System.arraycopy(elements, 0, destinationArray, 0, size);
        if (size < destinationArray.length) {
            destinationArray[size] = null;
        }
        return destinationArray;
    }

    @SuppressWarnings("unchecked")
    private E[] newElementArray(int capacity) {
        return (E[]) new Comparable[capacity];
    }

    private void removeAt(int index) {
        size--;
        if (size == index)
            elements[index] = null;
        else {
            E moved = elements[size];
            elements[size] = null;
            siftDown(index, moved);
            if (elements[index] == moved) {
                siftUp(index, moved);
            }
        }
    }

    /**
     * Inserts target at position childIndex, maintaining heap invariant by
     * promoting target up the tree until it is greater than or equal to its
     * parent, or is the root.
     *
     * @param childIndex
     *            the index used for the original placement of target.
     * @param target
     *            the item to be placed and sifted.
     */
    private void siftUp(int childIndex, E target) {
        while (childIndex > 0) {
            int parentIndex = (childIndex - 1) / 2;
            E parent = elements[parentIndex];
            if (target.compareTo(parent) >= 0)
                break;
            elements[childIndex] = parent;
            childIndex = parentIndex;
        }
        elements[childIndex] = target;
    }

    /**
     * Inserts target at position index, maintaining the heap invariant by
     * demoting target down the tree repeatedly until it is less than or equal
     * to its children or is a leaf.
     *
     * @param index the index where target should initially be placed.
     * @param target the item to be placed and sifted down.
     */
    private void siftDown(int index, E target) {
        int half = size / 2;
        while (index < half) {
            int childIndex = index * 2 + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < size
                    && child.compareTo(elements[rightIndex]) > 0) {
                childIndex = rightIndex;
                child = elements[childIndex];
            }
            if (target.compareTo(child) <= 0)
                break;
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = target;
    }

    private void growToSize(int size) {
        if (size > elements.length) {
            E[] newElements = newElementArray(size * DEFAULT_CAPACITY_RATIO);
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return Arrays.asList(elements).iterator();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder buffer = new StringBuilder(size * 16);
        buffer.append('[');
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E next = it.next();
            if (next != this) {
                buffer.append(next);
            } else {
                buffer.append("(this HeapArray)");
            }
            if (it.hasNext()) {
                buffer.append(", ");
            }
        }
        buffer.append(']');
        return buffer.toString();
    }
}