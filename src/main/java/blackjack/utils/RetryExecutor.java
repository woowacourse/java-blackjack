package blackjack.utils;

import blackjack.view.OutputView;
import java.util.function.Function;
import java.util.function.Supplier;

public final class RetryExecutor {

    private RetryExecutor() {

    }

    public static <T> T retry(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return retry(supplier);
        }
    }

    public static <T, R> R retry(final Function<T, R> function, final T t) {
        try {
            return function.apply(t);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return retry(function, t);
        }
    }
}
