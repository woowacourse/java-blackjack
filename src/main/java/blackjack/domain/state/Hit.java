package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Hit extends Running {

    public Hit(final PlayingCards playingCards) {
        super(playingCards);
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isBust()) {
            return new Bust(playingCards);
        }
        return this;
    }
}
