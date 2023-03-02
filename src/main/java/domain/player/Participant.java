package domain.player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participant extends Player {
    private GameResult gameResult;
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    @Override
    public void battle(Player player) {
        int totalScore = getTotalScore();
        int totalScoreOfOtherPlayer = player.getTotalScore();
        decideGameResult(totalScore, totalScoreOfOtherPlayer);
    }

    private void decideGameResult(int totalScore, int totalScoreOfOtherPlayer) {
        if (totalScore > totalScoreOfOtherPlayer) {
            this.gameResult = GameResult.WIN;
            return;
        }

        if (totalScore < totalScoreOfOtherPlayer) {
            this.gameResult = GameResult.LOSE;
            return;
        }

        this.gameResult = GameResult.DRAW;
    }

    @Override
    public List<Integer> getGameResult() {
        return Arrays.stream(GameResult.values())
                .map(this::isGameResultEqualTo)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean isNameEqualTo(String playerName) {
        return this.name.equals(playerName);
    }

    private int isGameResultEqualTo(GameResult gameResult) {
        if (this.gameResult == gameResult) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name) && gameResult == that.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gameResult);
    }
}
