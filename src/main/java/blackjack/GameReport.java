package blackjack;

import java.util.Objects;

public class GameReport {
    private final String name;
    private final GameResult gameResult;

    public GameReport(String name, int result) {
        this.name = name;
        this.gameResult = GameResult.findByResult(result);
    }

    public String getName() {
        return name;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameReport that = (GameReport) o;
        return Objects.equals(name, that.name) &&
                gameResult == that.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gameResult);
    }

}
