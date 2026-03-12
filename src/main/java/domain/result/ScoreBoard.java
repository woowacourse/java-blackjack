package domain.result;

import domain.common.BlackJackRule;
import domain.common.PlayedGameResult;
import domain.result.vo.DealerWinningScore;
import domain.result.vo.PlayerWinningInfo;
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
        Map<WinningCondition, Long> statistics = statisticsOfWinningConditions();

        return DealerWinningScore.of(
                statistics.getOrDefault(WinningCondition.WIN, 0L),
                statistics.getOrDefault(WinningCondition.DRAW, 0L),
                statistics.getOrDefault(WinningCondition.LOSE, 0L)
        );
    }

    private Map<WinningCondition, Long> statisticsOfWinningConditions() {
        return playerGameResults().stream()
                .map(this::determinePlayerWinningCondition)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private WinningCondition determineDealerWinningCondition(PlayedGameResult playerResult) {
        if (determinePlayerWinningCondition(playerResult) == WinningCondition.WIN) {
            return WinningCondition.LOSE;
        }

        if (determinePlayerWinningCondition(playerResult) == WinningCondition.LOSE) {
            return WinningCondition.WIN;
        }

        return WinningCondition.DRAW;
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
        return new PlayerWinningInfo(playerResult.name(), determinePlayerWinningCondition(playerResult).description());
    }

    private WinningCondition determinePlayerWinningCondition(PlayedGameResult playedGameResult) {
        if (playerWinningConditionIfPlayerBusted(playedGameResult)) {
            return WinningCondition.LOSE;
        }

        return determinePlayerWinningConditionIfPlayerNotBusted(playedGameResult);
    }

    private WinningCondition determinePlayerWinningConditionIfPlayerNotBusted(PlayedGameResult playedGameResult) {
        int dealerScore = dealerGameResult().scoreSum();

        if (dealerScore > BlackJackRule.BUST_NUMBER.value()) {
            return WinningCondition.WIN;
        }

        return playerWinningConditionIfBothNotBusted(playedGameResult, dealerScore);
    }

    private boolean playerWinningConditionIfPlayerBusted(PlayedGameResult playedGameResult) {
        return playedGameResult.scoreSum() > BlackJackRule.BUST_NUMBER.value();
    }

    private WinningCondition playerWinningConditionIfBothNotBusted(PlayedGameResult playedGameResult, int dealerScore) {
        if (dealerScore > playedGameResult.scoreSum()) {
            return WinningCondition.LOSE;
        }

        if (dealerScore == playedGameResult.scoreSum()) {
            return WinningCondition.DRAW;
        }

        return WinningCondition.WIN;
    }
}
