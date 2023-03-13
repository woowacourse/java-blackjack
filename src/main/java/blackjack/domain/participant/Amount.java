package blackjack.domain.participant;

import blackjack.domain.game.WinningResult;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Amount {

    private static final Pattern pattern = Pattern.compile("^[0-9]*$");
    private static final String PLAYER_AMOUNT_ERROR_MESSAGE = "플레이어의 배팅금액은 숫자만 가능합니다.";
    private static final String NULL_OR_BLANK_ERROR_MESSAGE = "null 또는 빈칸이 들어올 수 없습니다.";
    public static final String PLAYER_AMOUNT_RANGE = "배팅금액은 1000이상 100000이하여야 합니다.";
    public static final int MIN_AMOUNT = 1000;
    public static final int MAX_AMOUNT = 100000;

    private final BigDecimal amount;

    public Amount(final String amount) {
        validateAmount(amount);
        this.amount = new BigDecimal(amount);
    }

    public BigDecimal calculateAmountByResult(final WinningResult winningResult) {
        return amount.multiply(winningResult.getMagnification());
    }

    private void validateAmount(final String amount) {
        checkEmpty(amount);
        checkAmountNumber(amount);
        checkAmountRange(amount);
    }

    private void checkAmountRange(final String amount) {
        if (Integer.parseInt(amount) < MIN_AMOUNT || Integer.parseInt(amount) > MAX_AMOUNT) {
            throw new IllegalArgumentException(PLAYER_AMOUNT_RANGE);
        }
    }

    private void checkAmountNumber(final String amount) {
        Matcher nameMatcher = pattern.matcher(amount);
        if (!nameMatcher.matches()) {
            throw new IllegalArgumentException(PLAYER_AMOUNT_ERROR_MESSAGE);
        }
    }

    private void checkEmpty(final String amount) {
        if (amount == null || amount.isBlank()) {
            throw new IllegalArgumentException(NULL_OR_BLANK_ERROR_MESSAGE);
        }
    }
}
