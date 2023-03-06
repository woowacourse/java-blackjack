package techcourse.jcf.mission;

public interface SimpleList {

    boolean add(String value);

    void add(int index, String value);

    String set(int index, String value);

    String get(int index);

    boolean contains(String value);

    int indexOf(String value);

    int size();

    boolean isEmpty();

    boolean remove(String value);

    String remove(int index);

    void clear();
}
