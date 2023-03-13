package blackjack.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Repeater {

    private Repeater() {
    }

    public static <T> T repeatIfError(Supplier<T> operation, Consumer<Exception> handler) {
        try {
            return operation.get();
        } catch (Exception e) {
            handler.accept(e);
            return repeatIfError(operation, handler);
        }
    }
}
