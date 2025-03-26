package domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayerResults {
    private final List<PlayerWinningStatus> playerResult;

    public PlayerResults(List<PlayerWinningStatus> playerResult) {
        this.playerResult = new ArrayList<>(playerResult);
    }

    public PlayerWinningStatus findByPlayerName(String name) {
        return playerResult.stream()
                .filter(playerWinningStatus -> playerWinningStatus.playerName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 플레이어가 없습니다."));
    }

    public List<PlayerWinningStatus> getPlayerResult() {
        return Collections.unmodifiableList(playerResult);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerResults that = (PlayerResults) o;
        return Objects.equals(playerResult, that.playerResult);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerResult);
    }

    @Override
    public String toString() {
        return "PlayerResult{" +
                "playerResult=" + playerResult +
                '}';
    }
}
