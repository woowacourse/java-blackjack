package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public class Hit implements State {

    private final Deck deck;

    protected Hit(Deck deck) {
        this.deck = deck;
    }

    @Override
    public boolean drawable() {
        return true;
    }

    @Override
    public State draw(Card card) {
        deck.addCard(card);
        if(deck.isBust()) {
            return new Bust(deck);
        }
        return this;
    }

    @Override
    public Score score() {
        return deck.score();
    }
}
