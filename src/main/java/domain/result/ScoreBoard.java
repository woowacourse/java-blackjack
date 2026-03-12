package domain.result;

import domain.common.BlackJackRule;
import domain.common.PlayedGameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final PlayedGameResult dealerResult;
    private final List<PlayedGameResult> gameResults;

    public ScoreBoard() {
        this.dealerResult = null;
        this.gameResults = new ArrayList<>();
    }

    private ScoreBoard(PlayedGameResult dealerResult, List<PlayedGameResult> playerResults) {
        this.dealerResult = dealerResult;
        this.gameResults = List.copyOf(playerResults);
    }

    public void record(PlayedGameResult playerGameResult) {
        gameResults.add(playerGameResult);
    }

    public ScoreBoard recordDealerResult(PlayedGameResult dealerResult) {
        return new ScoreBoard(dealerResult, this.gameResults);
    }

    public List<PlayedGameResult> playerGameResults() {
        return List.copyOf(gameResults);
    }

    public PlayedGameResult dealerGameResult() {
        requireDealerGameResultExists();
        return dealerResult;
    }

    public DealerWinningScore dealerWinningScore() {
        Map<WinDrawLose, Long> statistics = statisticsOfWinningConditions();

        return DealerWinningScore.of(
                statistics.getOrDefault(WinDrawLose.WIN, 0L),
                statistics.getOrDefault(WinDrawLose.DRAW, 0L),
                statistics.getOrDefault(WinDrawLose.LOSE, 0L)
        );
    }

    private Map<WinDrawLose, Long> statisticsOfWinningConditions() {
        return playerGameResults().stream()
                .map(this::determineDealerWinningCondition)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private WinDrawLose determineDealerWinningCondition(PlayedGameResult playerResult) {
        if (determinePlayerWinDrawLose(playerResult) == WinDrawLose.WIN) {
            return WinDrawLose.LOSE;
        }

        if (determinePlayerWinDrawLose(playerResult) == WinDrawLose.LOSE) {
            return WinDrawLose.WIN;
        }

        return WinDrawLose.DRAW;
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

    private WinDrawLose determinePlayerWinDrawLose(PlayedGameResult playedGameResult) {
        if (isPlayerBusted(playedGameResult)) {
            return WinDrawLose.LOSE;
        }

        return determinePlayerWinningConditionIfPlayerNotBusted(playedGameResult);
    }

    private WinDrawLose determinePlayerWinningConditionIfPlayerNotBusted(PlayedGameResult playedGameResult) {
        int dealerScore = dealerGameResult().scoreSum();

        if (dealerScore > BlackJackRule.BUST_NUMBER.value()) {
            return WinDrawLose.WIN;
        }

        return determinePlayerWinDrawLoseIfBothNotBusted(playedGameResult, dealerScore);
    }

    private boolean isPlayerBusted(PlayedGameResult playedGameResult) {
        return playedGameResult.scoreSum() > BlackJackRule.BUST_NUMBER.value();
    }

    private WinDrawLose determinePlayerWinDrawLoseIfBothNotBusted(PlayedGameResult playedGameResult, int dealerScore) {
        if (dealerScore > playedGameResult.scoreSum()) {
            return WinDrawLose.LOSE;
        }

        if (dealerScore == playedGameResult.scoreSum()) {
            return WinDrawLose.DRAW;
        }

        return WinDrawLose.WIN;
    }
}
