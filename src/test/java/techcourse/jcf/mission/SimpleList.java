package techcourse.jcf.mission;

public interface SimpleList<E> {

    boolean add(E value);

    void add(int index, E value);

    Object set(int index, E value);

    E get(int index);

    boolean contains(E value);

    int indexOf(E value);

    int size();

    boolean isEmpty();

    boolean remove(E value);

    Object remove(int index);

    void clear();

    static <E> SimpleList<E> fromArrayToList(E[] values) {
        SimpleList<E> objectSimpleList = new SimpleArrayList<>();
        for (E value : values) {
            objectSimpleList.add(value);
        }
        return objectSimpleList;
    }

    static <E extends Number> double sum(SimpleList<E> simpleList) {
        double sum = 0.0;
        for (int i = 0; i < simpleList.size(); i++) {
            E convertValue = simpleList.get(i);
            sum += convertValue.doubleValue();
        }
        return sum;
    }

    static <E extends Number> SimpleList<E> filterNegative(SimpleList<E> simpleList) {
        for (int i = 0; i < simpleList.size(); i++) {
            if (simpleList.get(i).longValue() < 0L) {
                simpleList.remove(i);
            }
        }
        return simpleList;
    }

    static <E> void copy(SimpleList<? extends E> laserPrinter, SimpleList<? super E> printer) {
        for (int i = 0; i < laserPrinter.size(); i++) {
            printer.add(laserPrinter.get(i));
        }
    }
}
