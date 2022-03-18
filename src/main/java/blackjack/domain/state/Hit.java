package blackjack.domain.state;

import blackjack.domain.bet.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.game.PlayingCards;

public class Hit implements State {

    private final PlayingCards playingCards;
    private final Betting betting;

    public Hit(final PlayingCards playingCards, final Betting betting) {
        this.playingCards = playingCards;
        this.betting = betting;
    }

    @Override
    public State draw(final Card card) {
        playingCards.add(card);

        if (playingCards.isBust()) {
            return new Bust(playingCards, betting);
        }
        return this;
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
