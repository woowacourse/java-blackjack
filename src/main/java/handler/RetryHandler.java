package handler;

import java.util.function.Supplier;
import view.ErrorView;

public class RetryHandler {
    public static final int RETRY_LIMIT = 10;
    private static final String OVER_RETRY = String.format("입력 횟수 제한 %d를 초과하였습니다.", RETRY_LIMIT);
    private int retryCount;

    public <R> R retry(final Supplier<R> supplier) {
        validateRetryCountLimit();
        try {
            R value = supplier.get();
            retryCount = 0;
            return value;
        } catch (IllegalArgumentException exception) {
            ErrorView.printErrorMessage(exception.getMessage());
            return retry(supplier);
        }
    }

    public void retry(final Runnable runnable) {
        validateRetryCountLimit();
        try {
            runnable.run();
            retryCount = 0;
        } catch (IllegalArgumentException exception) {
            ErrorView.printErrorMessage(exception.getMessage());
            retry(runnable);
        }
    }


    private void validateRetryCountLimit() {
        if (retryCount++ == RETRY_LIMIT) {
            throw new IllegalArgumentException(OVER_RETRY);
        }
    }
}
