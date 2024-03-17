package blackjack.domain;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Betting {

    private static final Pattern BETTING_VALID_PATTERN = Pattern.compile("^[1-9][0-9]*$");
    private static final String INVALID_BETTING_FORMAT_EXCEPTION = "배팅 형식에 맞지 않습니다. 0 초과의 숫자만 입력해주세요.";

    private final BigDecimal betting;

    public Betting(String betting) {
        validateBetting(betting);
        this.betting = new BigDecimal(betting);
    }

    private void validateBetting(final String betting) {
        if (!BETTING_VALID_PATTERN.matcher(betting).matches()) {
            throw new IllegalArgumentException(INVALID_BETTING_FORMAT_EXCEPTION);
        }
    }

    public BigDecimal calculateProfit(final GameResult gameResult) {
        return gameResult.calculateProfit(betting);
    }
}
