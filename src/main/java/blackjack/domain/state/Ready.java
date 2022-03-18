package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Ready implements State {

    private final PlayingCards playingCards = new PlayingCards();

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isMoreDeal()) {
            return this;
        }
        return new Hit(playingCards);
    }
}
