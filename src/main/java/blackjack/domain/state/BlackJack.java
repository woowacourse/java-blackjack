package blackjack.domain.state;

import blackjack.domain.card.Card;

import java.util.List;

public class BlackJack implements State {
    private List<Card> cards;

    public BlackJack(List<Card> cars) {
        this.cards = cars;
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
        return null;
    }
}
