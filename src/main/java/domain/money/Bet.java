package domain.money;

import domain.match.MatchResult;

import java.math.BigDecimal;

public class Bet {

    private static final int ZERO = 0;
    private static final int MAX_BET_AMOUNT = 100_000_000;

    private static final BigDecimal WIN_WITH_BLACKJACK_PAYOUT_RATIO = new BigDecimal("1.5");
    private static final BigDecimal WIN_PAYOUT_RATIO = BigDecimal.ONE;
    private static final BigDecimal DRAW_PAYOUT_RATIO = BigDecimal.ZERO;
    private static final BigDecimal LOSE_PAYOUT_RATIO = new BigDecimal("-1");

    private final Money money;

    public Bet(int amount) {
        validatePositiveAmount(amount);
        validateMaxBetAmount(amount);
        this.money = Money.of(amount);
    }

    public Money calculateProfit(MatchResult result, boolean isBlackJack) {
        if (result == MatchResult.WIN) {
            if (isBlackJack) {
                return money.multiply(WIN_WITH_BLACKJACK_PAYOUT_RATIO);
            }

            return money.multiply(WIN_PAYOUT_RATIO);
        }

        if (result == MatchResult.DRAW) {
            return money.multiply(DRAW_PAYOUT_RATIO);
        }

        return money.multiply(LOSE_PAYOUT_RATIO);
    }

    private void validatePositiveAmount(int amount) {
        if (amount <= ZERO) {
            throw new IllegalArgumentException(String.format("잘못된 배팅 금액: %d (배팅 금액은 %d보다 커야 합니다.)", amount, ZERO));
        }
    }

    private void validateMaxBetAmount(int amount) {
        if (amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(String.format("잘못된 배팅 금액: %d (배팅 금액은 %d을 초과할 수 없습니다.)", amount, MAX_BET_AMOUNT));
        }
    }

    public Money getMoney() {
        return money;
    }
}
