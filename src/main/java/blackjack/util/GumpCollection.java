package blackjack.util;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;

public class GumpCollection {
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Queue<T> asQueue(T... a) {
        return new ArrayDeque<>(a);
    }

    private static class ArrayDeque<E> extends AbstractQueue<E>
            implements Serializable {
        transient int tail = 1;
        transient int head = 0;

        private Object[] q;


        ArrayDeque(E[] array) {
            q = Objects.requireNonNull(array);
        }

        //deprecated
        @Deprecated
        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public int size() {
            return q.length;
        }

        @Override
        public boolean offer(E e) {
            if (e == null)
                throw new NullPointerException();
            q[tail] = e;
            if ((tail = (tail + 1) & (q.length - 1)) == head) {
                doubleCapacity();
            }
            return true;
        }

        @Override
        public E poll() {
            int h = head;
            @SuppressWarnings("unchecked")
            E result = (E) q[h];
            // Element is null if deque empty
            if (result == null)
                return null;
            q[h] = null;     // Must null out slot
            head = (h + 1) & (q.length - 1);
            return result;
        }

        @Override
        public E peek() {
            return (E) q[head];
        }

        private void doubleCapacity() {
            assert head == tail;
            int p = head;
            int n = q.length;
            int r = n - p; // number of elements to the right of p
            int newCapacity = n << 1;
            if (newCapacity < 0)
                throw new IllegalStateException("Sorry, deque too big");
            Object[] a = new Object[newCapacity];
            System.arraycopy(q, p, a, 0, r);
            System.arraycopy(q, 0, a, r, p);
            q = a;
            head = 0;
            tail = n;
        }
    }
}
