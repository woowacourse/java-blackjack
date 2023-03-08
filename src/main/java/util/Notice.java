package util;

@FunctionalInterface
public interface Notice<T> {

    void print(T t);
}
