package blackjack.domain.user;

import blackjack.domain.result.Outcome;

public class Dealer extends User {

    private static final UserName DEALER_NAME = new UserName("딜러");
    private static final int SEVEN_TEEN = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < SEVEN_TEEN;
    }

    @Override
    public Outcome determineWinner(User player) {
        if (player.isBust()) {
            return Outcome.WIN;
        }
        if (isBust()) {
            return Outcome.LOSE;
        }
        if (isBlackjack() || player.isBlackjack()) {
            return compareBlackjack(player);
        }
        return compareScore(player);
    }

    private Outcome compareBlackjack(User player) {
        if (isBlackjack() && player.isBlackjack()) {
            return Outcome.DRAW;
        }
        if (isBlackjack()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private Outcome compareScore(User player) {
        int gap = getScore() - player.getScore();
        if (gap > 0) {
            return Outcome.WIN;
        }
        if (gap < 0) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }
}
