package blackjack.player.domain.report;

import blackjack.card.domain.GameResult;

import java.util.Objects;

public class GameReport {
    private final String name;
    private final GameResult gameResult;

    public GameReport(String name, GameResult gameResult) {
        validate(name, gameResult);
        this.name = name;
        this.gameResult = gameResult;
    }

    private void validate(String name, GameResult gameResult) {
        if (name == null) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
        if (gameResult == null) {
            throw new IllegalArgumentException("게임 결과가 비어있습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return gameResult.getMessage();
    }

    public boolean isWin() {
        return this.gameResult == GameResult.WIN;
    }

    public boolean isDraw() {
        return this.gameResult == GameResult.DRAW;
    }

    public boolean isLose() {
        return this.gameResult == GameResult.LOSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GameReport that = (GameReport) o;
        return Objects.equals(name, that.name) &&
                gameResult == that.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gameResult);
    }
}
