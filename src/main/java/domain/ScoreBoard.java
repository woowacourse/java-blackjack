package domain;

import domain.constant.BlackJackRule;
import domain.constant.WinningCondition;
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

    void record(PlayedGameResult playedGameResult) {
        gameResults.add(playedGameResult);
    }

    List<PlayedGameResult> playerGameResults() {
        return gameResults.stream()
                .filter(this::isPlayerResult)
                .toList();
    }

    private boolean isPlayerResult(PlayedGameResult result) {
        return !result.infos()
                .name()
                .equals("딜러");
    }

    PlayedGameResult dealerGameResult() {
        return gameResults.stream()
                .filter(result -> result.infos().name().equals("딜러"))
                .findFirst()
                .orElseThrow();
    }

    DealerWinningScore dealerWinningScore() {
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
                .map(playerResult -> {
                    if(playerWinningCondition(playerResult) == WinningCondition.WIN) {
                        return WinningCondition.LOSE;
                    }

                    if(playerWinningCondition(playerResult) == WinningCondition.LOSE) {
                        return WinningCondition.WIN;
                    }
                    return WinningCondition.DRAW;
                })
                .forEach(condition -> countMap.merge(condition, 1, Integer::sum));

        return countMap;
    }

    List<PlayerWinningInfo> playerWinningInfos() {
        return gameResults.stream()
                .map(this::playerWinningInfo)
                .toList();
    }

    private PlayerWinningInfo playerWinningInfo(PlayedGameResult playerResult) {
        return PlayerWinningInfo.from(playerResult.infos().name(), playerWinningCondition(playerResult));
    }

    private WinningCondition playerWinningCondition(PlayedGameResult playedGameResult) {
        if (winningConditionIfPlayerBusted(playedGameResult)) {
            return WinningCondition.LOSE;
        }
        return winningConditionIfPlayerNotBusted(playedGameResult);
    }

    private WinningCondition winningConditionIfPlayerNotBusted(PlayedGameResult playedGameResult) {
        int dealerScore = dealerGameResult().scoreSum();

        if (dealerScore > BlackJackRule.BUST_NUMBER.value()) {
            return WinningCondition.WIN;
        }

        return winningConditionIfBothNotBusted(playedGameResult, dealerScore);
    }

    private boolean winningConditionIfPlayerBusted(PlayedGameResult playedGameResult) {
        return playedGameResult.scoreSum() > BlackJackRule.BUST_NUMBER.value();
    }

    private WinningCondition winningConditionIfBothNotBusted(PlayedGameResult playedGameResult, int dealerScore) {
        if (dealerScore > playedGameResult.scoreSum()) {
            return WinningCondition.LOSE;
        }

        if (dealerScore == playedGameResult.scoreSum()) {
            return WinningCondition.DRAW;
        }

        return WinningCondition.WIN;
    }
}
