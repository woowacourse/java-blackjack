package domain.player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Participant extends Player {
    private GameResult gameResult;

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

    private int isGameResultEqualTo(GameResult gameResult) {
        if (this.gameResult == gameResult) {
            return 1;
        }
        return 0;
    }
}
