package blackjack.util;

import blackjack.view.OutputView;
import java.util.function.Supplier;

public class ExceptionHandler {

    public static <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
