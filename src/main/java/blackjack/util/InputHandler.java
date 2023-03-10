package blackjack.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InputHandler {

    public static <T, R> R retryForIllegalArgument(final T askValue, final Function<T, R> inputFunction,
                                                   final Consumer<String> exceptionMessageHandling) {
        try {
            return inputFunction.apply(askValue);
        } catch (final IllegalArgumentException exception) {
            exceptionMessageHandling.accept(exception.getMessage());
        }
        return retryForIllegalArgument(askValue, inputFunction, exceptionMessageHandling);
    }

    public static <T> T retryForIllegalArgument(final Supplier<T> input, final Consumer<T> domain,
                                                final Consumer<String> exceptionMessageHandling) {
        while (true) {
            try {
                final T argument = input.get();
                domain.accept(argument);
                return argument;
            } catch (final IllegalArgumentException exception) {
                exceptionMessageHandling.accept(exception.getMessage());
            }
        }
    }
}
