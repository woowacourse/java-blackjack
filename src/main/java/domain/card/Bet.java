package domain.card;

import domain.MatchResult;

public class Bet {

    private static final double WIN_WITH_BLACKJACK_PAYOUT_RATIO = 1.5;
    private static final int WIN_PAYOUT_RATIO = 1;
    private static final int DRAW_PAYOUT_RATIO = 0;
    private static final int LOSE_PAYOUT_RATIO = -1;

    private static final int MAX_BET_AMOUNT = 100_000_000;

    private final int amount;

    public Bet(int amount) {
        validateNonNegativeAmount(amount);
        validateMaxBetAmount(amount);
        this.amount = amount;
    }

    public int calculateProfit(MatchResult result, boolean isBlackJack) {
        if (result == MatchResult.WIN) {
            if (isBlackJack) return (int) (amount * WIN_WITH_BLACKJACK_PAYOUT_RATIO);

            return amount * WIN_PAYOUT_RATIO;
        }

        if (result == MatchResult.DRAW) return amount * DRAW_PAYOUT_RATIO;
        return amount * LOSE_PAYOUT_RATIO;
    }

    private void validateNonNegativeAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
    }

    private void validateMaxBetAmount(int amount) {
        if (amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(String.format("배팅 금액은 %d을 초과할 수 없습니다.", MAX_BET_AMOUNT));
        }
    }

    public int getAmount() {
        return amount;
    }
}
