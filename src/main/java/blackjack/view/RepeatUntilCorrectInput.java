package blackjack.view;

import java.util.function.Supplier;

public class RepeatUntilCorrectInput {

    public static <T> T repeat(Supplier<T> inputSupplier, OutputView outputView) {
        try {
            return inputSupplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printCorrectInput(e.getMessage());
            return repeat(inputSupplier, outputView);
        }
    }
}
