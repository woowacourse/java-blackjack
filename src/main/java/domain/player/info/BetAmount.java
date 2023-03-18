package domain.player.info;

import java.util.Objects;

public class BetAmount {

    private static final int LOSE = -1;
    private static final int TIE = 0;
    private static final int MIN_AMOUNT = 1000;
    private static final int MIN_UNIT = 100;

    private final int betAmount;

    private BetAmount(final int betAmount) {
        this.betAmount = betAmount;
    }

    public static BetAmount from(final int betAmount) {
        validateAmount(betAmount);
        return new BetAmount(betAmount);
    }

    private static void validateAmount(final int betAmount) {
        if (betAmount < MIN_AMOUNT) {
            throw new IllegalArgumentException("1000 이상만 베팅해주세요.");
        }

        if (betAmount % MIN_UNIT != 0) {
            throw new IllegalArgumentException("100 단위만 입력해주세요.");
        }
    }

    public int winBet(final boolean isBlackjack) {
        if (isBlackjack) {
            return (int) (betAmount * 1.5);

        }
        return betAmount;
    }

    public int loseBet() {
        return betAmount * LOSE;
    }

    public int returnBet() {
        return TIE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BetAmount betAmount1 = (BetAmount) o;
        return betAmount == betAmount1.betAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betAmount);
    }
}
