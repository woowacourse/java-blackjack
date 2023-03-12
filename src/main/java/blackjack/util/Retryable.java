package blackjack.util;

import blackjack.view.OutputView;

import java.util.function.Supplier;

public interface Retryable {

    static <T> T retryWhenException(Supplier<T> supplier, OutputView outputView) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException exception) {
                outputView.printException(exception.getMessage());
            }
        }
    }
}
