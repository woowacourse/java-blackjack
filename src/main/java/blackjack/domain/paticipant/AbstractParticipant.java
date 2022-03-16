package blackjack.domain.paticipant;

import blackjack.domain.card.Card;
import blackjack.domain.state.BlackjackGameState;
import java.util.List;
import java.util.Objects;

public abstract class AbstractParticipant {

    private final String name;
    BlackjackGameState blackjackGameState;

    AbstractParticipant(final String name, final BlackjackGameState blackjackGameState) {
        Objects.requireNonNull(name, "이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(blackjackGameState, "게임 상태는 null이 들어올 수 없습니다.");
        checkEmptyName(name);
        this.name = name;
        this.blackjackGameState = blackjackGameState;
    }

    private void checkEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 들어올 수 없습니다.");
        }
    }

    public void hit(final Card card) {
        blackjackGameState = blackjackGameState.hit(card);
    }

    public void stay() {
        blackjackGameState = blackjackGameState.stay();
    }

    public boolean isFinishied() {
        return blackjackGameState.isFinished();
    }

    public int score() {
        return blackjackGameState.score();
    }

    public List<Card> cards() {
        return blackjackGameState.cards();
    }

    public String getName() {
        return name;
    }
}
