package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Ready implements State {

    private final PlayingCards playingCards = new PlayingCards();
    private final Betting betting;

    public Ready(final Betting betting) {
        this.betting = betting;
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isMoreDeal()) {
            return this;
        }
        if (playingCards.isBlackjack()) {
            return new Blackjack(playingCards, betting);
        }
        return new Hit(playingCards, betting);
    }

    @Override
    public State stay() {
        return new Stay(playingCards, betting);
    }

    @Override
    public PlayingCards playingCards() {
        return playingCards;
    }
}
