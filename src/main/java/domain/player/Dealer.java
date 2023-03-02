package domain.player;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
    private final List<GameResult> gameResults;

    public Dealer() {
        super();
        gameResults = new ArrayList<>();
    }

    @Override
    public void battle(Player player) {
        int totalScore = getTotalScore();
        int totalScoreOfOtherPlayer = player.getTotalScore();
        decideGameResult(totalScore, totalScoreOfOtherPlayer);
    }

    private void decideGameResult(int totalScore, int totalScoreOfOtherPlayer) {
        if (totalScore > totalScoreOfOtherPlayer) {
            gameResults.add(GameResult.WIN);
            return;
        }

        if (totalScore < totalScoreOfOtherPlayer) {
            gameResults.add(GameResult.LOSE);
            return;
        }

        gameResults.add(GameResult.DRAW);
    }

    @Override
    public int getWinningCount() {
        return (int) gameResults.stream()
                .filter(GameResult::isWinning)
                .count();
    }

    @Override
    public int getLoseCount() {
        return (int) gameResults.stream()
                .filter(GameResult::isLose)
                .count();
    }

    @Override
    public int getDrawCount() {
        return (int) gameResults.stream()
                .filter(GameResult::isDraw)
                .count();
    }
}
