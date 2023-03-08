package blackjack.controller;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Repeater {

    public static <T> T repeatUntilNoException(final Supplier<T> supplier,
                                               final Consumer<Exception> exceptionHandler) {
        T result = null;
        while (result == null) {
            result = createOutputOrNull(supplier, exceptionHandler);
        }
        return result;
    }

    public static <T> T createOutputOrNull(final Supplier<T> inputSupplier,
                                           final Consumer<Exception> exceptionHandler) {
        try {
            return inputSupplier.get();
        } catch (final IllegalArgumentException e) {
            exceptionHandler.accept(e);
            return null;
        }
    }
}
