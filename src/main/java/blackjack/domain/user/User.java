package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import blackjack.domain.state.Stay;

import java.util.Objects;

public abstract class User {
    protected final Name name;
    protected State state;
    protected Money bettingMoney;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this.name = name;
    }

    public final void initializeCards(Cards cards) {
        state = StateFactory.generateStateByCards(cards);
    }

    public final boolean isAbleToHit() {
        return !state.isFinish();
    }

    public final Score score() {
        return state.cards().totalScore();
    }

    public final Cards cards() {
        return state.cards();
    }

    public final String getName() {
        return name.getName();
    }

    public State getState() {
        return state;
    }

    private void changeState(State state) {
        this.state = state;
    }

    public final void hit(Card card) {
        changeState(state.draw(card));
    }

    public final void stay() {
        changeState(new Stay(cards()));
    }

    public final void betMoney(Money money) {
        bettingMoney = money;
    }

    public final boolean isBlackjack() {
        return cards().totalScore()
                .isBlackjack();
    }

    public final boolean isBust() {
        return cards().isBust();
    }

    public final boolean isBlackJack() {
        return cards().isBlackjack();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(state.cards(), user.state.cards()) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state.cards(), name);
    }
}
