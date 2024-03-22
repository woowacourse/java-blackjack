package exception;

import java.util.function.Supplier;

public class ExceptionHandler {

    private ExceptionHandler() {}

    public static <T> T retry(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (IllegalArgumentException exception) {
            System.out.printf("[ERROR] %s%n%n", exception.getMessage());
            return retry(callback);
        }
    }
}
