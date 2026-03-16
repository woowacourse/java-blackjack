package domain.result;

import domain.common.BlackJackRule;
import domain.common.Money;
import dto.PlayedGameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoreBoard {

    private static final int BLACK_JACK_NUMBER = 21;

    private final DealerGameResult dealerGameState;
    private final Map<String, PlayerState> playerGameStates;

    public ScoreBoard() {
        this.dealerGameState = new DealerGameResult();
        this.playerGameStates = new LinkedHashMap<>();
    }

    public void setupPlayers(String name, Money bettingMoney) {
        playerGameStates.putIfAbsent(name, PlayerState.onlyBet(bettingMoney));
    }

    public void record(PlayedGameResult playedGameResult) {
        playerGameStates.computeIfPresent(
                playedGameResult.name(),
                (name, state) -> state.updatePlayedGameResult(playedGameResult)
        );
    }

    public void recordDealerResult(PlayedGameResult playedGameResult) {
        dealerGameState.record(playedGameResult);
    }

    public List<PlayedGameResult> playerGameResults() {
        return playerGameStates.values()
                .stream()
                .map(PlayerState::playedGameResult)
                .toList();
    }

    public PlayedGameResult dealerGameResult() {
        return dealerGameState.dealerResult();
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

    public List<PlayerWinningInfo> playerWinningInfos() {
        return playerGameResults().stream()
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
