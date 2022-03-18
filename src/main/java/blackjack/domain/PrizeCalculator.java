package blackjack.domain;

import blackjack.domain.participant.PlayerStatus;

public enum PrizeCalculator {

    BLACKJACK(1.5, 0, 0),
    BUST(-1, -1, -1),
    DEFAULT(1, -1, 0),
    ;

    private final double winEarningRate;
    private final double lossEarningRate;
    private final double pushEarningRate;

    PrizeCalculator(final double winEarningRate, final double lossEarningRate, final double pushEarningRate) {
        this.winEarningRate = winEarningRate;
        this.lossEarningRate = lossEarningRate;
        this.pushEarningRate = pushEarningRate;
    }

    public static PrizeCalculator from(final PlayerStatus playerStatus) {
        if (playerStatus == PlayerStatus.BUST) {
            return BUST;
        }
        if (playerStatus == PlayerStatus.BLACKJACK) {
            return BLACKJACK;
        }
        return DEFAULT;
    }

    public double calculate(final int playerScore, final int dealerScore, final boolean dealerBlackjack,
                            final int bettingAmount) {
        if (this == BLACKJACK) {
            return calculateWhenBlackjack(dealerBlackjack, bettingAmount);
        }
        if (dealerScore > 21) {
            return calculateWhenDealerBust(bettingAmount);
        }

        return calculateWhenDefault(playerScore, dealerScore, bettingAmount);
    }

    private double calculateWhenBlackjack(final boolean dealerBlackjack, final int bettingAmount) {
        if (dealerBlackjack) {
            return pushEarningRate * bettingAmount;
        }
        return winEarningRate * bettingAmount;
    }

    private double calculateWhenDealerBust(final int bettingAmount) {
        return winEarningRate * bettingAmount;
    }

    private double calculateWhenDefault(final int playerScore, final int dealerScore, final int bettingAmount) {
        if (playerScore > dealerScore) {
            return winEarningRate * bettingAmount;
        }
        if (playerScore < dealerScore) {
            return lossEarningRate * bettingAmount;
        }
        return pushEarningRate * bettingAmount;
    }
}
