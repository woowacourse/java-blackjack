package blackjack.domain.user;

import blackjack.domain.result.Outcome;

public class Player extends User {

    private static final int TWENTY_ONE = 21;

    public Player(UserName playerName) {
        super(playerName);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < TWENTY_ONE;
    }

    @Override
    public Outcome determineWinner(User dealer) {
        if (dealer.isBust() && isBust()) {
            return Outcome.DRAW;
        }
        if (isBust()) {
            return Outcome.LOSE;
        }
        if (dealer.isBust()) {
            return Outcome.WIN;
        }
        if (isBlackjack() || dealer.isBlackjack()) {
            return compareBlackjack(dealer);
        }
        return compareScore(dealer);
    }

    private Outcome compareBlackjack(User dealer) {
        if (isBlackjack() && dealer.isBlackjack()) {
            return Outcome.DRAW;
        }
        if (isBlackjack()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private Outcome compareScore(User dealer) {
        int gap = getScore() - dealer.getScore();
        if (gap > 0) {
            return Outcome.WIN;
        }
        if (gap < 0) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }
}
