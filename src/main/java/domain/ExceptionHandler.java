package domain;

import java.util.function.Supplier;

public class ExceptionHandler {
    public static <T> T handle(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (BlackjackException e) {
            System.out.printf("%n[ERROR] %s%n", e.getMessage());
            return handle(callback);
        }
    }
}
