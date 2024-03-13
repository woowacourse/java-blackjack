package domain;

import java.util.function.Supplier;

public class ExceptionHandler {
    public static <T> T handle(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (IllegalArgumentException e) {
            System.out.printf("[재입력] %s\n", e.getMessage());
            return handle(callback);
        }
    }
}
