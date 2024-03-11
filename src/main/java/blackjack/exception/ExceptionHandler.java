package blackjack.exception;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionHandler {

    private static final int MAX_RETRY_COUNT = 1000;

    private static int retryCount = 0;

    private ExceptionHandler() {
    }

    public static <T> T retryWithSupplier(final Supplier<T> supplier, final Consumer<String> errorConsumer) {
        try {
            return supplier.get();
        } catch (final NeedRetryException e) {
            errorConsumer.accept(e.getMessage());
            validateRetryCount();
            return retryWithSupplier(supplier, errorConsumer);
        }
    }

    public static void retryWithRunnable(final Runnable runnable, final Consumer<String> errorConsumer) {
        try {
            runnable.run();
        } catch (final NeedRetryException e) {
            errorConsumer.accept(e.getMessage());
            validateRetryCount();
            retryWithRunnable(runnable, errorConsumer);
        }
    }

    private static void validateRetryCount() {
        if (retryCount++ >= MAX_RETRY_COUNT) {
            throw new IllegalArgumentException("재시도 횟수를 초과했습니다.");
        }
    }

}
