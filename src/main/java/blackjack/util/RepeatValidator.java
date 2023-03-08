package blackjack.util;

import blackjack.view.ErrorOutputView;

import java.util.function.Supplier;

public class RepeatValidator {

    public static <T> T readUntilValidate(Supplier<T> expression) {
        T input = null;
        do {
            input = trySupplier(expression);
        } while (input == null);

        return input;
    }

    private static <T> T trySupplier(Supplier<T> expression) {
        try {
            return expression.get();
        } catch (IllegalArgumentException exception) {
            ErrorOutputView.printError(exception);
            return null;
        }
    }
}
