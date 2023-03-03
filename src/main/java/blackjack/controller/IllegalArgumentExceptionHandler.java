package blackjack.controller;

import blackjack.view.OutputView;

import java.util.function.Function;
import java.util.function.Supplier;

public class IllegalArgumentExceptionHandler {

    public static <Input, Output> Output repeatUntilNoException(final Supplier<Input> supplier,
                                                                final Function<Input, Output> function,
                                                                final OutputView outputView) {
        Output output = null;

        while (output == null) {
            output = createOutputOrNull(supplier, function, outputView);
        }
        return output;
    }

    public static <Input, Output> Output createOutputOrNull(final Supplier<Input> inputSupplier,
                                                            final Function<Input, Output> function,
                                                            final OutputView outputView) {
        try {
            final Input input = inputSupplier.get();
            return function.apply(input);

        } catch (final IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return null;
        }
    }

}
