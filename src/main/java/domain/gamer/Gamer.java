package domain.gamer;

import domain.deck.Card;
import domain.state.State;
import java.util.List;
import java.util.Objects;

public abstract class Gamer {

    private final Nickname nickname;
    protected State state;

    public Gamer(final Nickname nickname, final State state) {
        this.nickname = nickname;
        this.state = state;
    }

    public abstract List<Card> getInitialCards();

    public int calculateSumOfRank() {
        return getHand().calculateSumOfRank();
    }

    public void hit(final Card card) {
        this.state = state.hit(card);
    }

    public boolean isRunning() {
        return !state.isFinished();
    }

    public void stay() {
        this.state = state.stay();
    }

    public boolean isBlackjack() {
        return state.getHand().isBlackjack();
    }

    public int compareTo(final Gamer otherGamer) {
        return state.compareTo(otherGamer.getState());
    }

    public Hand getHand() {
        return state.getHand();
    }

    public String getDisplayName() {
        return nickname.getDisplayName();
    }

    public State getState() {
        return state;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final Gamer gamer)) {
            return false;
        }
        return Objects.equals(nickname, gamer.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }
}

