package blackjack.utils;

import blackjack.view.OutputView;
import java.util.function.Function;
import java.util.function.Supplier;

public class RetryExecutor {

    public static <T> T retry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return retry(supplier);
        }
    }

    public static <T, R> R retry(Function<T, R> function, T t) {
        try {
            return function.apply(t);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return retry(function, t);
        }
    }
}
