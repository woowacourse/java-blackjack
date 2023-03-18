package domain.participant;

import java.math.BigDecimal;

public final class PlayerBet {

    private static final int AMOUNT_UNIT = 1000;
    private static final int MINIMUM_AMOUNT = 1000;

    private final BigDecimal bet;

    private PlayerBet(final BigDecimal bet) {
        this.bet = bet;
    }

    public static PlayerBet create(final int betAmount) {
        validateMinimumBetAmount(betAmount);
        validateBetAmountUnit(betAmount);

        final BigDecimal bet = new BigDecimal(betAmount);

        return new PlayerBet(bet);
    }

    public BigDecimal calculateBenefit(final double prizeRatio) {
        final BigDecimal ratio = new BigDecimal(prizeRatio);
        final BigDecimal prize = bet.multiply(ratio);

        if (prize.compareTo(BigDecimal.ZERO) < 0) {
            return prize;
        }
        return prize.subtract(bet);
    }

    private static void validateMinimumBetAmount(final int betAmount) {
        if (betAmount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("최소 천 원 이상 배팅해주세요.");
        }
    }

    private static void validateBetAmountUnit(final int betAmount) {
        if (betAmount % AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("천 원 단위로 배팅해주세요.");
        }
    }
}
