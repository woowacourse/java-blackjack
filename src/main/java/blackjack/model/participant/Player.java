package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.state.State;
import blackjack.model.state.finished.FinishedState;
import blackjack.model.state.running.InitialDeal;
import java.util.Objects;

public final class Player implements CardReceivable {

    private final Name name;
    private State state;

    public Player(Name name) {
        this.name = name;
        state = new InitialDeal();
    }

    @Override
    public void receiveCard(Card card) {
        state = state.receiveCard(card);
    }

    public void stand() {
        state = state.stand();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Name getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public FinishedState getFinishedState() {
        if (!(state instanceof FinishedState finishedState)) {
            throw new IllegalArgumentException("플레이어의 턴이 종료되지 않았습니다.");
        }
        return finishedState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
