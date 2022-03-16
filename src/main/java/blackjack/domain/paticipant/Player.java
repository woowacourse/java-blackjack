package blackjack.domain.paticipant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.PlayerRunning;
import java.util.Objects;

public class Player {

    private final String name;
    private final int betMoney;
    private BlackjackGameState gameState;

    public Player(final String name, final int betMoney, final BlackjackGameState gameState) {
        Objects.requireNonNull(name, "이름은 null이 들어올 수 없습니다.");
        Objects.requireNonNull(gameState, "게임 상태는 null이 들어올 수 없습니다.");
        checkEmptyName(name);
        checkNotPositiveBetMoney(betMoney);
        this.name = name;
        this.betMoney = betMoney;
        this.gameState = gameState;
    }

    public Player(final String name, final int betMoney, final Cards cards) {
        this(name, betMoney, new PlayerRunning(cards));
    }

    private void checkEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 들어올 수 없습니다.");
        }
    }

    private void checkNotPositiveBetMoney(final int betMoney) {
        if (betMoney <= 0) {
            throw new IllegalArgumentException("배팅금액은 0이하의 값이 들어올 수 없습니다.");
        }
    }

    public void hit(final Card card) {
        gameState = gameState.hit(card);
    }

    public void stay() {
        gameState = gameState.stay();
    }

    public boolean isFinishied() {
        return gameState.isFinished();
    }
}
