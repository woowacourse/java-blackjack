package blackjack.view;

public class StringToIntegerParser {

    public static int parse(final String numberText) {
        try {
            return Integer.parseInt(numberText);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("올바른 숫자를 입력해 주세요.");
        }
    }
}
