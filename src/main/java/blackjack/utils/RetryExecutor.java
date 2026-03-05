package blackjack.utils;

import blackjack.view.OutputView;
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
}
