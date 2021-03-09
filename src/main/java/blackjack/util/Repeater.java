package blackjack.util;

import blackjack.view.OutputView;

import java.util.function.Supplier;

public abstract class Repeater {
    public static <T> T repeatOnError(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printError(e);
            return repeatOnError(supplier);
        }
    }
}
