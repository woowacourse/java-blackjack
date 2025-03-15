package domain.gamer;

public class Betting {

    private static final int BET_AMOUNT_MIN_VALUE = 1_000;
    private static final int BET_AMOUNT_MAX_VALUE = 100_000;
    private static final double BLACKJACK_BONUS = 1.5;

    private final int betAmount;

    public Betting(final int betAmount) {
        validateMaxAndMinAmount(betAmount);
        this.betAmount = betAmount;
    }

    private void validateMaxAndMinAmount(final int betAmount) {
        if (betAmount < BET_AMOUNT_MIN_VALUE || betAmount > BET_AMOUNT_MAX_VALUE) {
            throw new IllegalArgumentException("배팅 금액은 1,000원 이상, 100,000원 이하여야 합니다.");
        }
    }

    public int winBlackJackBetting(final int profit) {
        return (int) (profit + (betAmount * BLACKJACK_BONUS));
    }

    public int winBetting(final int profit) {
        return profit + betAmount;
    }

    public int loseBetting(final int profit) {
        return profit - betAmount;
    }
}
