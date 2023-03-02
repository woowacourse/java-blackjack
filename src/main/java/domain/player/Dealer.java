package domain.player;

import java.util.*;
import java.util.stream.Collectors;

public class Dealer extends Player {
    private final Map<GameResult, Integer> gameResults;

    public Dealer() {
        super();
        gameResults = new EnumMap<>(GameResult.class);
        initGameResult();
    }

    private void initGameResult() {
        for (GameResult gameResult : GameResult.values()) {
            gameResults.put(gameResult, 0);
        }
    }

    @Override
    public void battle(Player player) {
        int totalScore = getTotalScore();
        int totalScoreOfOtherPlayer = player.getTotalScore();
        decideGameResult(totalScore, totalScoreOfOtherPlayer);
    }

    private void decideGameResult(int totalScore, int totalScoreOfOtherPlayer) {
        if (totalScore > totalScoreOfOtherPlayer) {
            gameResults.put(GameResult.WIN, getGameResultCount(GameResult.WIN) + 1);
            return;
        }

        if (totalScore < totalScoreOfOtherPlayer) {
            gameResults.put(GameResult.LOSE, getGameResultCount(GameResult.LOSE) + 1);
            return;
        }

        gameResults.put(GameResult.DRAW, getGameResultCount(GameResult.DRAW) + 1);
    }

    private int getGameResultCount(GameResult gameResult) {
        return gameResults.getOrDefault(gameResult, 0);
    }

    @Override
    public List<Integer> getGameResult() {
        return Arrays.stream(GameResult.values())
                .map(this.gameResults::get)
                .collect(Collectors.toUnmodifiableList());
    }
}
