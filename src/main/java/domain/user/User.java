package domain.user;

import domain.card.Card;
import domain.card.Score;
import domain.dto.UserDto;
import domain.state.Ready;
import domain.state.State;

import java.util.List;

public abstract class User {
    private final Name name;
    private State state;

    public User(Name name, Ready ready) {
        this.name = name;
        this.state = ready;
    }

    public final void receiveCards(List<Card> cards) {
        cards.forEach(this::receiveCard);
    }

    public final boolean isName(Name playerName) {
        return getName().equals(playerName);
    }

    public final boolean hasLessScore(User other) {
        return getScore().isLessThan(other.getScore());
    }

    public final boolean hasSameScore(User other) {
        return getScore().equals(other.getScore());
    }

    public final UserDto getUserDto() {
        return new UserDto(getName(), getScore(), getCards());
    }

    public void receiveCard(Card card) {
        state = state.draw(card);
    }

    public void doStay() {
        state = state.stay();
    }

    public boolean hasResult() {
        return state.isFinished();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public List<Card> getCards() {
        return state.getCards();
    }

    public Score getScore() {
        return state.getScore();
    }

    public Name getName() {
        return name;
    }

    protected State getState() {
        return state;
    }

    public abstract double getProfitRatio();
}
