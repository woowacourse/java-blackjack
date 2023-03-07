package techcourse.jcf.mission;

public interface SimpleList<E> {

    boolean add(E value);

    void add(int index, E value);

    E set(int index, E value);

    E get(int index);

    boolean contains(E value);

    int indexOf(E value);

    int size();

    boolean isEmpty();

    E remove(int index);

    boolean remove(E value);

    void clear();

    static <E> SimpleList<E> fromArrayToList(E... elements) {
        SimpleList<E> list = new SimpleArrayList<>();
        for (E object: elements){
            list.add(object);
        }
        return list;
    }

    static <T extends Number> double sum(SimpleList<T> list) {
        double sum = 0;
        for (int i = 0; i< list.size(); i++) {
            sum += list.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> list) {
        SimpleList<T> copy = new SimpleArrayList<>();
        for (int i = 0; i< list.size(); i++) {
            if(list.get(i).doubleValue() >= 0) {
                copy.add(list.get(i));
            }
        }
        return copy;
    }

    static <T> void copy(SimpleList<? extends T> source, SimpleList<? super T> destination) {
        destination.clear();

        for (int i = 0; i< source.size(); i++) {
            destination.add(source.get(i));
        }
    }
}

