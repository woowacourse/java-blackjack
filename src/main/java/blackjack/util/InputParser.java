package blackjack.util;

public final class InputParser {

    public static int parseInt(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }
}
