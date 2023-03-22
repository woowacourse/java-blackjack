package techcourse.jcf.mission;

public interface SimpleList<E> {

    <E> SimpleList<E> fromArrayToList(E[] arrays);

    boolean add(E value);

    void add(int index, E value);

    E set(int index, E value);

    E get(int index);

    boolean contains(E value);

    int indexOf(E value);

    int size();

    boolean isEmpty();

    boolean remove(E value);

    E remove(int index);

    void clear();

    static <E extends Number> Number sum(SimpleList<? extends Number> simpleList) {
        Number n1 = simpleList.get(0);
        Number n2 = simpleList.get(1);
        if (!n1.getClass().equals(n2.getClass())) {
            throw new IllegalArgumentException("같은 클래스가 아닙니다. n1 : " + n1.getClass() + ", n2 : " + n2.getClass());
        }
        if (n1.getClass().equals(Double.class)) {
            return (Double) n1 + (Double) n2;
        }
        if (n1.getClass().equals(Integer.class)) {
            return (Integer) n1 + (Integer) n2;
        }
        if (n1.getClass().equals(Long.class)) {
            return (Long) n1 + (Long) n2;
        }
        return (Float) n1 + (Float) n2;
    }

    static <E extends Number> SimpleArrayList<E> filterNegative(SimpleList<? extends Number> simpleList){
        Number[] numbers = {simpleList.get(0),simpleList.get(1),simpleList.get(2)};
        return new SimpleArrayList<E>(numbers);

    }

    static <E> void copy(SimpleList<? extends Printer> from, SimpleList<? super Printer> to){
        for (int i = 0; i < from.size(); i++) {
             Printer copiedElement = from.get(i);
             to.add(copiedElement);
        }
    }
}
