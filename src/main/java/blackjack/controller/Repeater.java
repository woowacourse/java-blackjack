package blackjack.controller;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Repeater {

    private Repeater() {
    }

    public static <T> T repeatUntilNoException(final Supplier<T> supplier,
                                               final Consumer<String> exceptionHandler) {
        T result = null;
        while (result == null) {
            result = createOutputOrNull(supplier, exceptionHandler);
        }
        return result;
    }

    private static <T> T createOutputOrNull(final Supplier<T> inputSupplier,
                                            final Consumer<String> exceptionHandler) {
        try {
            return inputSupplier.get();
        } catch (final IllegalArgumentException e) {
            exceptionHandler.accept(e.getMessage());
            return null;
        }
    }
}
