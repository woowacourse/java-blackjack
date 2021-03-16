package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

import java.util.List;
import java.util.Objects;

public class Dealer extends User {
    public static final Score TURN_OVER_COUNT = Score.Of(17);

    private static final Name name = new Name("딜러");
    private State state;

    public Dealer(List<Card> cards) {
        this.state = StateFactory.createState(cards.get(0), cards.get(1));
    }

    public boolean canAddCard() {
        return state.score().lessThan(TURN_OVER_COUNT);
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public void stayIfNotFinished() {
        if (!state.isFinished()) {
            state = state.stay();
        }
    }

    public boolean isBust() {
        return state.isBust();
    }

    public Score score() {
        return state.score();
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
    public void addCard(Card card) {
        this.state = state.draw(card);
    }

    @Override
    public int scoreToInt() {
        return state.score().toInt();
    }

    @Override
    public boolean isFinished() {
        return state.isFinished();
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
