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
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public double profit(State enemyState) {
        return -1;
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
