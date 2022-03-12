package blackjack.domain.player;

import blackjack.domain.participant.GameStatus;
import java.util.Objects;

public class Player {

    private final String name;
    private GameStatus gameStatus;

    public Player(final String name, final GameStatus gameStatus) {
        Objects.requireNonNull(name, "이름이 null이 들어올 수 없습니다.");
        validateBlankName(name);
        this.name = name;
        this.gameStatus = gameStatus;
    }

    private void validateBlankName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름이 공백이 들어올 수 없습니다.");
        }
    }

    public void changeGameStatus() {
        if (gameStatus.isFinishedGame()) {
            throw new IllegalStateException("이미 종료된 게임은 종료요청을 할 수 없습니다.");
        }
    }
}
