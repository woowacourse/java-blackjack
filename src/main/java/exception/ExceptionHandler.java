package exception;

import java.util.Optional;
import java.util.function.Supplier;
import view.OutputView;

public class ExceptionHandler {

    public static void repeatUntilSuccess(Runnable runner) {
        boolean success = false;
        while (!success) {
            success = run(runner);
        }
    }

    public static <T> T repeatUntilSuccess(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = getSuccessResult(supplier);
        }
        return result.get();
    }

    private static boolean run(Runnable runner) {
        try {
            runner.run();
            return true;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return false;
        }
    }

    private static <T> Optional<T> getSuccessResult(Supplier<T> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
