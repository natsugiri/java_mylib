import java.util.*;

/**
 * int size();
 * boolean isEmpty();
 * void clear();
 * void addFirst(E e);
 * void addLast(E e);
 * E set(int index, E e);
 * E get(int index);
 * E getFirst();
 * E getLast();
 * void removeFirst();
 * void removeLast();
 * Iterator<E> iterator();
 * ListIterator<E> listIterator();
 */
class RandomAccessDeque<E> extends AbstractSequentialList<E> {
    public ListIterator<E> listIterator(int index) { return new Itr(); }
    public Iterator<E> iterator() { return new Itr(); }

    private int head;
    private int count;
    Object[] array;

    RandomAccessDeque() {
	head = 0;
	count = 0;
	array = null;
    }

    private void expandIfFull() {
	if (array == null) {
	    array = new Object[1];
	} else if (array.length == count) {
	    Object[] tmp = new Object[array.length * 2];
	    for (int i = 0; i < count; i++) {
		tmp[i] = array[(head + i) & (count - 1)];
	    }
	    array = tmp;
	    head = 0;
	}
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
	if (index < 0 || count <= index) {
	    throw new NoSuchElementException(String.format("index = %d\n", index));
	}
	return count == 0 ? null : (E) array[(head + index) & (array.length - 1)];
    }

    public E set(int index, E e) {
	E ret = get(index);
	array[(head + index) & (array.length - 1)] = e;
	return ret;
    }

    public int size() { return count; }

    public E getLast() { return get(count - 1); }

    public E getFirst() { return get(0); }

    public E removeLast() {
	E ret = set(count - 1, null);
	count--;
	return ret;
    }

    public E removeFirst() {
	E ret = set(0, null);
	count--;
	head = (head + 1) & (array.length - 1);
	return ret;
    }

    public void addLast(E e) {
	expandIfFull();
	count++;
	set(count - 1, e);
    }

    public void addFirst(E e) {
	expandIfFull();
	count++;
	head = (head == 0 ? array.length - 1 : head - 1);
	set(0, e);
    }

    public void clear() {
	while (!isEmpty()) { removeLast(); }
    }

    private class Itr implements ListIterator<E> {
	int index;
	Itr() { index = 0; }
	public boolean hasNext() { return index < count; }
	public E next() { return get(index++); }
	public void set(E e) { RandomAccessDeque.this.set(index - 1, e); }

	public void add(E e) { throw new UnsupportedOperationException(); }
	public void remove() { throw new UnsupportedOperationException(); }
	public int previousIndex() { throw new UnsupportedOperationException(); }
	public int nextIndex() { throw new UnsupportedOperationException(); }
	public boolean hasPrevious() { return false; }
	public E previous() { throw new UnsupportedOperationException(); }
    }
}
