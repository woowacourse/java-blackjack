package blackjack.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RetryUtil {

    public static <T> T retryOnInvalidInput(Supplier<T> retryableAction, Consumer< IllegalArgumentException> errorHandler) {
        try {
            return retryableAction.get();
        } catch (IllegalArgumentException e) {
            errorHandler.accept(e);

            return retryOnInvalidInput(retryableAction, errorHandler);
        }
    }

    public static void retryOnInvalidInput(Runnable retryableAction, Consumer< IllegalArgumentException> errorHandler) {
        try {
            retryableAction.run();
        } catch (IllegalArgumentException e) {
            errorHandler.accept(e);

            retryOnInvalidInput(retryableAction, errorHandler);
        }
    }
}
