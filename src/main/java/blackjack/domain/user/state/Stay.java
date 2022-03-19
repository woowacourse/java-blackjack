package blackjack.domain.user.state;

import blackjack.domain.user.Hand;

public class Stay extends Finished {

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    protected boolean isBust() {
        return false;
    }

    @Override
    public double calculateEarningRate(State opponentState) {
        if (opponentState.isBust()) {
            return 1;
        }
        if (opponentState.isBlackjack()) {
            return -1;
        }
        return calculateEarningRateByScore(opponentState);
    }

    private double calculateEarningRateByScore(State opponentState) {
        if (this.calculateScore().isGreaterThan(opponentState.calculateScore())) {
            return 1; // 이김
        }
        if (opponentState.calculateScore().isGreaterThan(this.calculateScore())) {
            return -1; // 짐
        }
        return 0;
    }
}
