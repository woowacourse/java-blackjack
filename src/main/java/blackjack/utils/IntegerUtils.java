package blackjack.utils;

public class IntegerUtils {

    private IntegerUtils() {
    }

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력된 값이 숫자가 아닙니다.");
        }
    }
}
