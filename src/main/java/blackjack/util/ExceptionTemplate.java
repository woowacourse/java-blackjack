package blackjack.util;

import java.util.function.Supplier;

public abstract class ExceptionTemplate {
    private static final int MAX_REPEAT_COUNT = 20;

    public static <T> T repeatAndPrintCause(Supplier<T> supplier) {
        int count = 0;
        while (count++ < MAX_REPEAT_COUNT) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
        throw new IllegalStateException(String.format("최대 횟수 %d번 반복을 초과하였습니다.", MAX_REPEAT_COUNT));
    }
}
