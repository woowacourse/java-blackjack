package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

import java.util.List;
import java.util.Objects;

public class Dealer extends User {
    public static final int TURN_OVER_COUNT = 16;

    private static final Name name = new Name("딜러");
    private State state;

    public boolean canAddCard() {
        return !state.score().isOver(TURN_OVER_COUNT);
    }

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    protected List<Card> getCards() {
        return state.getCards();
    }

    @Override
    public void addFirstCards(Card card1, Card card2) {
        this.state = StateFactory.createState(card1, card2);
    }

    @Override
    public void addCard(Card card) {
        this.state = state.draw(card);
    }

    @Override
    public int score() {
        return state.score().toInt();
    }

    @Override
    public boolean isGameOver() {
        return state.isGameOver();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(state, dealer.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
