package domain.result;

import domain.common.BlackJackRule;
import domain.common.PlayedGameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final DealerGameResult dealerResult;
    private final List<PlayedGameResult> gameResults;

    public ScoreBoard() {
        this.dealerResult = new DealerGameResult();
        this.gameResults = new ArrayList<>();
    }

    public void record(PlayedGameResult playerGameResult) {
        gameResults.add(playerGameResult);
    }

    public void recordDealerResult(PlayedGameResult playerGameResult) {
        dealerResult.record(playerGameResult);
    }

    public List<PlayedGameResult> playerGameResults() {
        return List.copyOf(gameResults);
    }

    public PlayedGameResult dealerGameResult() {
        requireDealerGameResultExists();
        return dealerResult.playedGameResult();
    }

    public DealerWinningScore dealerWinningScore() {
        Map<GameOutcome, Long> statistics = statisticsOfWinningConditions();

        return DealerWinningScore.of(
                statistics.getOrDefault(GameOutcome.WIN, 0L),
                statistics.getOrDefault(GameOutcome.DRAW, 0L),
                statistics.getOrDefault(GameOutcome.LOSE, 0L)
        );
    }

    private Map<GameOutcome, Long> statisticsOfWinningConditions() {
        return playerGameResults().stream()
                .map(this::determineDealerWinningCondition)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private GameOutcome determineDealerWinningCondition(PlayedGameResult playerResult) {
        if (determinePlayerWinDrawLose(playerResult) == GameOutcome.WIN) {
            return GameOutcome.LOSE;
        }

        if (determinePlayerWinDrawLose(playerResult) == GameOutcome.LOSE) {
            return GameOutcome.WIN;
        }

        return GameOutcome.DRAW;
    }

    private void requireDealerGameResultExists() {
        if (dealerResult == null) {
            throw new IllegalStateException("딜러 기록이 저장되지 않았습니다.");
        }
    }

    public List<PlayerWinningInfo> playerWinningInfos() {
        return gameResults.stream()
                .map(this::playerWinningInfo)
                .toList();
    }

    private PlayerWinningInfo playerWinningInfo(PlayedGameResult playerResult) {
        return new PlayerWinningInfo(playerResult.name(), determinePlayerWinDrawLose(playerResult));
    }

    private GameOutcome determinePlayerWinDrawLose(PlayedGameResult playedGameResult) {
        if (isPlayerBusted(playedGameResult)) {
            return GameOutcome.LOSE;
        }

        return determinePlayerWinningConditionIfPlayerNotBusted(playedGameResult);
    }

    private GameOutcome determinePlayerWinningConditionIfPlayerNotBusted(PlayedGameResult playedGameResult) {
        int dealerScore = dealerGameResult().scoreSum();

        if (dealerScore > BlackJackRule.BUST_NUMBER.value()) {
            return GameOutcome.WIN;
        }

        return determinePlayerWinDrawLoseIfBothNotBusted(playedGameResult, dealerScore);
    }

    private boolean isPlayerBusted(PlayedGameResult playedGameResult) {
        return playedGameResult.scoreSum() > BlackJackRule.BUST_NUMBER.value();
    }

    private GameOutcome determinePlayerWinDrawLoseIfBothNotBusted(PlayedGameResult playedGameResult, int dealerScore) {
        if (dealerScore > playedGameResult.scoreSum()) {
            return GameOutcome.LOSE;
        }

        if (dealerScore == playedGameResult.scoreSum()) {
            return GameOutcome.DRAW;
        }

        return GameOutcome.WIN;
    }
}
