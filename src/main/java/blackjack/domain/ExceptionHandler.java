package blackjack.domain;

@FunctionalInterface
public interface ExceptionHandler<T> {
    T execute();
}
