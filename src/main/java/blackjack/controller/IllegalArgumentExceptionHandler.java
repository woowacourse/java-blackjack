package blackjack.controller;

import blackjack.view.OutputView;
import java.util.function.Function;
import java.util.function.Supplier;

public class IllegalArgumentExceptionHandler {

    public static <T, R> R repeatUntilNoException(final Supplier<T> supplier,
            final Function<T, R> function,
            final OutputView outputView) {
        R result = null;
        while (result == null) {
            result = createOutputOrNull(supplier, function, outputView);
        }
        return result;
    }

    public static <T, R> R createOutputOrNull(final Supplier<T> inputSupplier,
            final Function<T, R> function,
            final OutputView outputView) {
        try {
            final T result = inputSupplier.get();
            return function.apply(result);
        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return null;
        }
    }
}
