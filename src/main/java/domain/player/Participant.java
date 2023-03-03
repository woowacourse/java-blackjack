package domain.player;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participant extends Player {
    private GameResult gameResult;

    public Participant(String name) {
        super(name);
    }

    @Override
    public void battle(Player player) {
        if (isBurst() || player.isBurst()) {
            decideGameResultOnBurst(player);
            return;
        }

        int totalScore = getTotalScore();
        int totalScoreOfOtherPlayer = player.getTotalScore();
        decideGameResultOnScore(totalScore, totalScoreOfOtherPlayer);
    }

    private void decideGameResultOnBurst(Player player) {
        if (isBurst() && player.isBurst()) {
            decideGameResult(GameResult.DRAW);
            return;
        }

        if (isBurst()) {
            decideGameResult(GameResult.LOSE);
            return;
        }

        decideGameResult(GameResult.WIN);
    }

    private void decideGameResultOnScore(int totalScore, int totalScoreOfOtherPlayer) {
        if (totalScore > totalScoreOfOtherPlayer) {
            decideGameResult(GameResult.WIN);
            return;
        }

        if (totalScore < totalScoreOfOtherPlayer) {
            decideGameResult(GameResult.LOSE);
            return;
        }

        decideGameResult(GameResult.DRAW);
    }

    private void decideGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public List<Integer> getGameResult() {
        return Arrays.stream(GameResult.values())
                .map(this::isGameResultEqualTo)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean isNameEqualTo(String playerName) {
        return getName().equals(playerName);
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
        return Objects.equals(getName(), that.getName()) && gameResult == that.gameResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), gameResult);
    }
}
