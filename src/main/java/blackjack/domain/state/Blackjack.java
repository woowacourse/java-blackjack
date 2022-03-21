package blackjack.domain.state;

import blackjack.domain.game.PlayingCards;

public class Blackjack extends Finished {

    public Blackjack(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
