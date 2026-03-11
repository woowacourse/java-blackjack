package blackjack.util;

public final class InputParser {

    public static int parseInt(String target) {
        try {
            int number = Integer.parseInt(target);

            if (number < 0) {
                throw new IllegalArgumentException("자연수가 아닙니다.");
            }

            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }
}
