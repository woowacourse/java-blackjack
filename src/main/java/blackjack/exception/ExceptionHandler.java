package blackjack.exception;

import blackjack.view.OutputView;
import java.util.Optional;
import java.util.function.Supplier;

public class ExceptionHandler {

    public static <T> T repeatUntilSuccess(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = getSuccessResult(supplier);
        }
        return result.get();
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
