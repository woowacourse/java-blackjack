package blackjack.util;

import blackjack.view.OutputView;

import java.util.function.Supplier;

public final class RetryHandler {
    
    private static final int MAX_RETRY_COUNT = 5;
    
    private final OutputView outputView;
    
    public RetryHandler(final OutputView outputView) {
        this.outputView = outputView;
    }
    
    public <T> T runWithRetry(final Supplier<T> supplier) {
        int retryCount = 0;
        while (retryCount <= MAX_RETRY_COUNT) {
            try {
                return supplier.get();
            } catch (Exception e) {
                outputView.outputExceptionMessage(e);
                retryCount++;
            }
        }
        throw new IllegalStateException("최대 재시도 횟수를 초과했습니다.");
    }
    
    public void runWithRetry(final Runnable supplier) {
        int retryCount = 0;
        while (retryCount <= MAX_RETRY_COUNT) {
            try {
                supplier.run();
            } catch (Exception e) {
                outputView.outputExceptionMessage(e);
                retryCount++;
            }
        }
        throw new IllegalStateException("최대 재시도 횟수를 초과했습니다.");
    }
}
