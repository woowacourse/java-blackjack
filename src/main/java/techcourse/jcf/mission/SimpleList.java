package techcourse.jcf.mission;

public interface SimpleList<T> {


    boolean add(T value);

    void add(int index, T value);

    T set(int index, T value);

    T get(int index);

    boolean contains(T value);

    int indexOf(T value);

    int size();

    boolean isEmpty();

    boolean remove(T value);

    T remove(int index);

    void clear();

    static <T extends Number> Double sum(SimpleList<T> list) {
        double number = 0;
        for (int i = 0; i < list.size(); i++) {
            number += list.get(i).doubleValue();
        }
        return number;
    }
//
//    static SimpleList<? extends Number> filterNegative(SimpleList<? extends Number> list) {
//        SimpleList<? extends Number> result = new SimpleArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).intValue() >= 0) {
//                result.add(list.get(i));
//            }
//        }
//        return result;
//    }


}
