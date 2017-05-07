import java.util.*;

/*
Customized collection
*/
public class MyCollection<E> implements Collection<E> {
    private Object[] items;

    private int len;

    public MyCollection(int capacity) {
        items = new Object[10];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public void clear() {
        items = new Object[0];
        len = 0;
    }

    public Iterator<E> iterator() {
        return new MyIterator<>(this);
    }

    public boolean retainAll(Collection<?> reserve) {
        boolean result = false;
        if (reserve != null) {
            int i = 0;
            while (i < len) {
                Object item = items[i];
                if (!reserve.contains(item)) {
                    for (int j = i + 1; j < len; j++) {
                        items[j - 1] = items[j];
                    }

                    len--;
                    result = true;
                } else {
                    i++;
                }
            }
        }

        return result;
    }

    public boolean removeAll(Collection<?> data) {
        boolean result = false;
        if (data != null) {
            for (Object o : data) {
                result = remove(o) || result;
            }
        }

        return result;
    }

    public boolean addAll(Collection<? extends E> data) {
        boolean result = false;
        if (data != null) {
            for (E e : data) {
                result = add(e) || result;
            }
        }

        return result;
    }

    public boolean contains(Object item) {
        boolean result = false;
        if (item != null) {
            for (int i = 0; i < len; i++) {
                if (items[i] == item) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    public boolean containsAll(Collection<?> data) {
        boolean result = true;
        if (data != null) {
            for (Object e : data) {
                if (!contains(e)) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    public boolean remove(Object item) {
        boolean result = false;
        if (item != null) {
            for (int i = 0; i < len; i++) {
                if (items[i] == item) {
                    for (int j = i + 1; j < len; j++) {
                        items[j - 1] = items[j];
                    }

                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    public boolean add(E item) {
        makesureSize();
        items[len++] = item;
        return false;
    }

    public Object[] toArray() {
        return Arrays.copyOf(items, len);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] arrayToFill) {
        if (arrayToFill.length < len) {
            arrayToFill = Arrays.copyOf(arrayToFill, len);
        }

        for (int i = 0; i < len; i++) {
            arrayToFill[i] = (T)items[i];
        }

        return arrayToFill;
    }

    private void makesureSize() {
        if (len >= items.length) {
            items = Arrays.copyOf(items, len * 2);
        }
    }

    private class MyIterator<E> implements Iterator<E> {
        private int index;

        private MyCollection<E> co;

        public MyIterator(MyCollection<E> collection) {
            co = collection;
            index = 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            return (E) co.items[index++];
        }

        public boolean hasNext() {
            return index < co.size();
        }
    }
}