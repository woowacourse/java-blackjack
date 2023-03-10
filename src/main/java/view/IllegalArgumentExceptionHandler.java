package view;

import java.util.function.Supplier;

public class IllegalArgumentExceptionHandler {

    private static final int MAX_RECURSIVE = 1000;

    public static <T> T handleByRepeating(Supplier<T> supplier) {
        return handleByRepeating(supplier, 0);
    }

    private static <T> T handleByRepeating(Supplier<T> supplier, int loopCount) {
        try {
            if (loopCount > MAX_RECURSIVE) {
                throw new IllegalStateException("너무 많이 시도했습니다");
            }
            return supplier.get();
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return handleByRepeating(supplier, loopCount + 1);
        }
    }
}
