package util;

import java.util.function.Supplier;

public class LoopTemplate {

    private LoopTemplate() {

    }

    public static <T> T tryCatchLoop(Supplier<T> callback) {
        try {
            return callback.get();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            return tryCatchLoop(callback);
        }
    }

    public static void tryCatchLoop(Runnable callback) {
        try {
            callback.run();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            tryCatchLoop(callback);
        }
    }
}
