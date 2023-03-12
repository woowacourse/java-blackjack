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

    public static void runUntilValidate(InputFunction expression) {
        boolean isSuccess = false;
        do {
            isSuccess = tryInputFunction(expression);
        } while (!isSuccess);
    }

    private static boolean tryInputFunction(final InputFunction expression) {
        try {
            expression.run();
            return true;
        } catch (IllegalArgumentException exception) {
            ErrorOutputView.printError(exception);
            return false;
        }
    }
}
