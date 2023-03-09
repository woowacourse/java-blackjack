package blackjack.util;


import blackjack.view.OutputView;

import java.util.function.Supplier;

public interface Retryable {
    OutputView outputView = OutputView.getInstance();

    static <T> T retryWhenIllegalArgumentException(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
