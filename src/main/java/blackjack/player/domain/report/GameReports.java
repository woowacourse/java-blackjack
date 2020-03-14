package blackjack.player.domain.report;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameReports {
    private final List<GameReport> gameReports;

    public GameReports(List<GameReport> gameReports) {
        validate(gameReports);
        this.gameReports = gameReports;
    }

    private void validate(List<GameReport> gameReports) {
        if (gameReports == null || gameReports.isEmpty()) {
            throw new IllegalArgumentException("게임결과가 존재하지 않습니다.");
        }
    }

    public int getGamblerWinCount() {
        return (int) gameReports.stream()
                .filter(GameReport::isWin)
                .count();
    }

    public int getGamblerDrawCount() {
        return (int) gameReports.stream()
                .filter(GameReport::isDraw)
                .count();
    }

    public int getGamblerLoseCount() {
        return (int) gameReports.stream()
                .filter(GameReport::isLose)
                .count();
    }

    public List<GameReport> getReports() {
        return Collections.unmodifiableList(this.gameReports);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameReports that = (GameReports) o;
        return Objects.equals(gameReports, that.gameReports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameReports);
    }
}
