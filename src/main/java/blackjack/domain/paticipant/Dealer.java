package blackjack.domain.paticipant;

import blackjack.domain.state.BlackjackGameState;
import java.util.Objects;

public class Dealer {

    private BlackjackGameState gameState;

    public Dealer(final BlackjackGameState gameState) {
        Objects.requireNonNull(gameState, "게임 상태는 null이 들어올 수 없습니다.");
        this.gameState = gameState;
    }
}
