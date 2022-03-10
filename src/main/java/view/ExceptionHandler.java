package view;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionHandler {
    public static <T> T process(Supplier<T> action, Consumer<T> validator) {
        try {
            T t = action.get();
            validator.accept(t);
            return t;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return process(action, validator);
        }
    }
}
