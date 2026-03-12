package domain;

public class BettingParser {

    public static Bet parse(String input) {
        validate(input);
        return new Bet(parseInt(input));
    }

    private static void validate(String input) {
        validateNotBlank(input);
    }

    private static void validateNotBlank(String rawPlayerName) {
        if (rawPlayerName == null || rawPlayerName.isBlank()) {
            throw new IllegalArgumentException("베팅 금액이 비어 있습니다.");
        }
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력해 주세요.");
        }
    }
}
