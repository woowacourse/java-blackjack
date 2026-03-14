package domain;

import java.math.BigDecimal;

public class BettingParser {

    public static Bet parse(String input) {
        validateNotBlank(input);
        return new Bet(toBigDecimal(input));
    }

    private static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("베팅 금액이 비어 있습니다.");
        }
    }

    private static BigDecimal toBigDecimal(String input) {
        try {
            return new BigDecimal(input.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자(정수 또는 소수)만 입력해 주세요.");
        }
    }
}
