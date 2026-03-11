package domain.game_result;

import domain.common.BlackJackRule;
import domain.game_result.vo.DealerWinningScore;
import domain.common.PlayedGameResult;
import domain.game_result.vo.PlayerWinningInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

    private final List<PlayedGameResult> gameResults;

    public ScoreBoard() {
        this.gameResults = new ArrayList<>();
    }

    public void record(PlayedGameResult playedGameResult) {
        gameResults.add(playedGameResult);
    }

    public List<PlayedGameResult> playerGameResults() {
        return gameResults.stream()
                .filter(this::isPlayerResult)
                .toList();
    }

    private boolean isPlayerResult(PlayedGameResult result) {
        return !result.infos()
                .name()
                .equals("딜러");
    }

    public PlayedGameResult dealerGameResult() {
        return gameResults.stream()
                .filter(result -> result.infos().name().equals("딜러"))
                .findFirst()
                .orElseThrow();
    }

    public DealerWinningScore dealerWinningScore() {
        Map<WinningCondition, Integer> statistics = statisticsOfWinningConditions();

        return new DealerWinningScore(
                statistics.get(WinningCondition.WIN),
                statistics.get(WinningCondition.DRAW),
                statistics.get(WinningCondition.LOSE)
        );
    }

    private Map<WinningCondition, Integer> statisticsOfWinningConditions() {
        Map<WinningCondition, Integer> countMap = new LinkedHashMap<>();
        Arrays.stream(WinningCondition.values())
                .forEach(condition -> countMap.putIfAbsent(condition, 0));

        playerGameResults().stream()
                .map(this::playerConditionToDealerCondition)
                .forEach(condition -> countMap.merge(condition, 1, Integer::sum));

        return countMap;
    }

    private WinningCondition playerConditionToDealerCondition(PlayedGameResult playerResult) {
        if (playerWinningCondition(playerResult) == WinningCondition.WIN) {
            return WinningCondition.LOSE;
        }

        if (playerWinningCondition(playerResult) == WinningCondition.LOSE) {
            return WinningCondition.WIN;
        }

        return WinningCondition.DRAW;
    }

    public List<PlayerWinningInfo> playerWinningInfos() {
        return gameResults.stream()
                .filter(this::isPlayerResult)
                .map(this::playerWinningInfo)
                .toList();
    }

    private PlayerWinningInfo playerWinningInfo(PlayedGameResult playerResult) {
        return new PlayerWinningInfo(playerResult.infos().name(), playerWinningCondition(playerResult).description());
    }

    private WinningCondition playerWinningCondition(PlayedGameResult playedGameResult) {
        if (playerWinningConditionIfPlayerBusted(playedGameResult)) {
            return WinningCondition.LOSE;
        }

        return playerWinningConditionIfPlayerNotBusted(playedGameResult);
    }

    private WinningCondition playerWinningConditionIfPlayerNotBusted(PlayedGameResult playedGameResult) {
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
