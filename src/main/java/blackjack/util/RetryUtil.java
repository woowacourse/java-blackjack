package blackjack.util;

import java.util.function.Supplier;

public class RetryUtil {

    public static <T> T getReturnWithRetry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (Exception e) {
                System.out.println("[Error] " + e.getMessage());
            }
        }
    }

}
