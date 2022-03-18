package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Hit implements State {

    private final PlayingCards playingCards;

    public Hit(final PlayingCards playingCards) {
        this.playingCards = playingCards;
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
