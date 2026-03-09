package blackjack.utils;

import java.util.function.Supplier;

public class RetryInput {

    public RetryInput() {
    }

    public <T> T read(Supplier<T> reader) {
        while (true) {
            try {
                return reader.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
