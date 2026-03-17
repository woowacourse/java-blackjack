package domain.result;

import domain.Money;
import domain.ProfitInfo;
import domain.PlayedGameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

    private final Map<String, PlayerState> playerGameStates;

    public ScoreBoard() {
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

    public List<PlayedGameResult> playerGameResults() {
        return playerGameStates.values()
                .stream()
                .map(PlayerState::playedGameResult)
                .toList();
    }

    public ProfitInfo profitInfoByDealer(PlayedGameResult dealerGameResult) {
        return new ProfitInfo(dealerGameResult.name(), evaluateDealerProfit(dealerGameResult));
    }

    public List<ProfitInfo> evaluatePlayerProfitInfosWith(PlayedGameResult dealerGameResult) {
        return playerGameStates.keySet().stream()
                .map(playerName -> profitInfo(playerName, dealerGameResult))
                .toList();
    }

    private List<Money> evaluatePlayerProfits(PlayedGameResult dealerGameResult) {
        return playerGameStates.values()
                .stream()
                .map(playerState -> evaluatePlayerProfit(playerState, dealerGameResult))
                .toList();
    }

    private ProfitInfo profitInfo(String name, PlayedGameResult dealerGameResult) {
        return new ProfitInfo(name, evaluatePlayerProfit(playerGameStates.get(name), dealerGameResult));
    }

    private Money evaluatePlayerProfit(PlayerState playerState, PlayedGameResult dealerGameResult) {
        return determinePlayerGameOutcome(playerState, dealerGameResult).calculate(playerState.bettingMoney());
    }

    private Money evaluateDealerProfit(PlayedGameResult dealerGameResult) {
        List<Money> playerPayouts = evaluatePlayerProfits(dealerGameResult);

        long lossSum = playerPayouts.stream().mapToLong(Money::amount).sum();
        return Money.of(Math.negateExact(lossSum));
    }

    private PlayerGameOutcome determinePlayerGameOutcome(PlayerState playerState, PlayedGameResult dealerGameResult) {
        if (playerState.isBusted()) {
            return PlayerGameOutcome.LOSE;
        }

        return determinePlayerGameOutcomeIfPlayerNotBusted(playerState, dealerGameResult);
    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfPlayerNotBusted(PlayerState playerState,
                                                                          PlayedGameResult dealerGameResult) {
        if (playerState.isBlackJack()) {
            return determinePlayerGameOutcomeIfPlayerIsBlackJack(dealerGameResult);
        }

        if (dealerGameResult.isBusted()) {
            return PlayerGameOutcome.WIN;
        }

        return determinePlayerGameOutcomeIfBothNotBusted(playerState, dealerGameResult);
    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfPlayerIsBlackJack(PlayedGameResult dealerGameResult) {
        if (dealerGameResult.isBlackJack()) {
            return PlayerGameOutcome.DRAW;
        }
        return PlayerGameOutcome.BLACK_JACK_WIN;
    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfBothNotBusted(PlayerState playerState,
                                                                        PlayedGameResult dealerGameResult) {
        int playerScoreSum = playerState.scoreSum();
        int dealerScoreSum = dealerGameResult.scoreSum();

        return determinePlayerGameOutcomeIfBothNotBustedCompareWith(dealerScoreSum, playerScoreSum);
    }

    private PlayerGameOutcome determinePlayerGameOutcomeIfBothNotBustedCompareWith(int dealerScoreSum,
                                                                                   int playerScoreSum) {
        if (dealerScoreSum > playerScoreSum) {
            return PlayerGameOutcome.LOSE;
        }

        if (dealerScoreSum == playerScoreSum) {
            return PlayerGameOutcome.DRAW;
        }

        return PlayerGameOutcome.WIN;
    }
}
