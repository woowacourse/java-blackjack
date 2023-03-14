package domain.player;

import domain.score.Score;

public final class Player extends Gambler{

    public Player(final PlayerName playerName) {
        super(playerName);
    }

    public Status compareWithDealer(final Dealer dealer) {
        if (isBothBlackjack(dealer)) {
            return Status.DRAW;
        }
        if (this.isBlackjack()) {
            return Status.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack() || this.isBusted()) {
            return Status.LOSE;
        }
        if (dealer.isBusted()) {
            return Status.WIN;
        }
        return this.compareNormalCase(dealer.getScore());
    }

    private boolean isBothBlackjack(final Dealer dealer) {
        return dealer.isBlackjack() && this.isBlackjack();
    }

    private Status compareNormalCase(final Score score) {
        return getScore().compareScore(score);
    }
}
