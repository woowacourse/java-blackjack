package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Ready extends Running {

    Ready(final PlayingCards playingCards) {
        super(playingCards);
    }

    public Ready() {
        this(new PlayingCards());
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isMoreDeal()) {
            return this;
        }
        if (playingCards.isBlackjack()) {
            return new Blackjack(playingCards);
        }
        return new Hit(playingCards);
    }
}
