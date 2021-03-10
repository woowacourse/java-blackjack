package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public class Bust implements State {

    private final Deck deck;

    protected Bust(Deck deck) {
        this.deck = deck;
    }

    @Override
    public boolean drawable() {
        return false;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score score() {
        return deck.score();
    }

    @Override
    public int winningMoney(int batMoney) {
        return -batMoney;
    }
}
