package blackjack.domain.paticipant;

import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.DealerRunning;
import java.util.Objects;

public class Dealer {

    private BlackjackGameState gameState;

    private Dealer(final BlackjackGameState gameState) {
        Objects.requireNonNull(gameState, "게임 상태는 null이 들어올 수 없습니다.");
        this.gameState = gameState;
    }

    public Dealer(final Cards cards) {
        this(DealerRunning.createDealerGameState(cards));
    }
}
