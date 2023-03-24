package view;

import java.util.function.Supplier;

public class IllegalArgumentExceptionHandler {

    private static final int MAX_RECURSIVE = 1000;

    public static void handleByRepeating(Runnable runnable) {
        handleByRepeating(runnable, 0);
    }

    public static <T> T handleByRepeating(Supplier<T> supplier) {
        return handleByRepeating(supplier, 0);
    }

    private static void handleByRepeating(Runnable runnable, int loopCount) {
        try {
            validate(loopCount);
            runnable.run();
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            handleByRepeating(runnable, loopCount + 1);
        }
    }

    private static <T> T handleByRepeating(Supplier<T> supplier, int loopCount) {
        try {
            validate(loopCount);
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return handleByRepeating(supplier, loopCount + 1);
        }
    }

    private static void validate(int loopCount) {
        if (loopCount > MAX_RECURSIVE) {
            throw new IllegalStateException("너무 많이 시도했습니다");
        }
    }
}
