package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;
import blackjack.domain.state.State;
import java.util.Objects;

public abstract class AbstractPlayer implements Player {

    protected Name name;
    protected State state;

    @Override
    public void hit(Card card) {
        state = state.draw(card);
    }

    @Override
    public void stay() {
        state = state.stay();
    }

    @Override
    public boolean isBust() {
        return state.isBust();
    }

    @Override
    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public PlayerCards getPlayerCards() {
        return state.getCards();
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getScore() {
        return state
                .getCards()
                .getTotalScore();
    }

    @Override
    public boolean canHit() {
        return !state.isFinished();
    }

    public abstract boolean isDealer();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equals(name, that.name) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state);
    }
}
