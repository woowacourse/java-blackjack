package template;

@FunctionalInterface
public interface ExceptionHandler<T> {
    T execute();
}
