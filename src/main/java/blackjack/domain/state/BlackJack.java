package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class BlackJack implements State {
    private Cards cards;

    public BlackJack(List<Card> cards) {
        this.cards = new Cards(cards);
    }

    @Override
    public boolean isEndState() {
        return true;
    }

    @Override
    public double profit() {
        return 1.5;
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
