package blackjack.util;

import blackjack.view.OutputView;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Repeater {
    public static <T> T supplier(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printError(e);
            return supplier(supplier);
        }
    }

    public static <T, R> R supplierGetsArgumentOfFunction(Function<T, R> function, Supplier<T> supplier) {
        try {
            return function.apply(supplier.get());
        } catch (RuntimeException e) {
            OutputView.printError(e);
            return supplierGetsArgumentOfFunction(function, supplier);
        }
    }
}
