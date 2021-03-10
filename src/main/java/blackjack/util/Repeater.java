package blackjack.util;

import blackjack.view.OutputView;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Repeater {
    public static <T, R> R supplierGetsArgumentOfFunction(Function<T, R> function, Supplier<T> supplier) {
        try {
            return function.apply(supplier.get());
        } catch (RuntimeException e) {
            OutputView.printError(e);
            return supplierGetsArgumentOfFunction(function, supplier);
        }
    }
}
