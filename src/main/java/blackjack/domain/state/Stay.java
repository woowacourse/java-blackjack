package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Stay implements State {
    private Cards cards;

    public Stay(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public boolean isEndState() {
        return true;
    }

    @Override
    public double profit() {
        return 1;
    }

    @Override
    public void draw(Card card) {
    }

    @Override
    public State changeState() {
        return this;
    }

    @Override
    public State stay() {
        return this;
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
