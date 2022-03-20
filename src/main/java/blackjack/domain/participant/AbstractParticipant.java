package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.state.BlackjackGameState;
import java.util.List;
import java.util.Objects;

public abstract class AbstractParticipant implements Participant {

    private final Name name;
    protected BlackjackGameState blackjackGameState;

    AbstractParticipant(final Name name, final BlackjackGameState blackjackGameState) {
        Objects.requireNonNull(name, "이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(blackjackGameState, "게임 상태는 null이 들어올 수 없습니다.");
        this.name = name;
        this.blackjackGameState = blackjackGameState;
    }

    public void hit(final Card card) {
        blackjackGameState = blackjackGameState.hit(card);
    }

    public void stay() {
        blackjackGameState = blackjackGameState.stay();
    }

    public boolean isFinished() {
        return blackjackGameState.isFinished();
    }

    public int score() {
        return blackjackGameState.score();
    }

    public List<Card> cards() {
        return blackjackGameState.cards();
    }

    public String getName() {
        return name.getName();
    }
}
