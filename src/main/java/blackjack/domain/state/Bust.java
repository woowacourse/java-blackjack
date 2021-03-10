package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Bust implements State{
    private Cards cards;

    public Bust(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public boolean isEndState() {
        return true;
    }

    @Override
    public double profit() {
        return 0;
    }

    @Override
    public void draw(Card card) {

    }

    @Override
    public State changeState() {
        return null;
    }

    @Override
    public State stay() {
        return this;
    }
}
