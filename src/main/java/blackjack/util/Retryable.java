package blackjack.util;


import blackjack.view.OutputView;

import java.util.function.Supplier;

public interface Retryable {
    OutputView outputView = OutputView.getInstance();

    static <T> T retryWhenException(Supplier<T> supplier) {
        T result;

        do {
            result = checkIllegalArgumentException(supplier);
        } while (result == null);

        return result;
    }

    private static <T> T checkIllegalArgumentException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}
