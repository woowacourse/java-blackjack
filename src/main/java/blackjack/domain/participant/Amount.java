package blackjack.domain.participant;

import blackjack.domain.game.WinningResult;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Amount {

    private static final Pattern pattern = Pattern.compile("^[0-9]*$");
    private static final String PLAYER_AMOUNT_ERROR_MESSAGE = "플레이어의 이름은 숫자,영어,한글만 가능합니다.";
    private static final String NULL_OR_BLANK_ERROR_MESSAGE = "null 또는 빈칸이 들어올 수 없습니다.";
    public static final String PLAYER_AMOUNT_RANGE = "배팅금액은 1000이상 100000이하여야 합니다.";
    public static final int MIN_AMOUNT = 1000;
    public static final int MAX_AMOUNT = 100000;

    private BigDecimal amount;

    public Amount(String amount) {
        validateAmount(amount);
        this.amount = new BigDecimal(amount);
    }

    public void calculateAmountByResult(WinningResult winningResult) {
        this.amount = amount.multiply(winningResult.getMagnification());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void validateAmount(String amount) {
        checkEmpty(amount);
        checkAmountNumber(amount);
        checkAmountRange(amount);
    }

    private void checkAmountRange(String amount) {
        if (Integer.parseInt(amount) > MIN_AMOUNT || Integer.parseInt(amount) <= MAX_AMOUNT) {
            throw new IllegalArgumentException(PLAYER_AMOUNT_RANGE);
        }
    }

    private void checkAmountNumber(String amount) {
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
