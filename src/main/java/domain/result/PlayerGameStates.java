package domain.result;

import domain.Money;
import domain.PlayedGameResult;
import domain.ProfitInfo;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class PlayerGameStates {

    private final Map<String, PlayerState> playerGameStates;

    PlayerGameStates() {
        this.playerGameStates = new LinkedHashMap<>();
    }

    void setupPlayerBettingMoney(String name, Money bettingMoney) {
        playerGameStates.putIfAbsent(name, PlayerState.onlyBet(bettingMoney));
    }

    void record(PlayedGameResult playedGameResult) {
        playerGameStates.computeIfPresent(
                playedGameResult.name(),
                (name, state) -> state.updatePlayedGameResult(playedGameResult)
        );
    }

    List<PlayedGameResult> playerGameResults() {
        return playerGameStates.values()
                .stream()
                .map(PlayerState::playedGameResult)
                .toList();
    }

    List<ProfitInfo> evaluatePlayerProfitInfosWith(PlayedGameResult dealerGameResult) {
        return playerGameStates.keySet().stream()
                .map(playerName -> profitInfo(playerName, dealerGameResult))
                .toList();
    }

    List<Money> evaluatePlayerProfits(PlayedGameResult dealerGameResult) {
        return playerGameStates.values()
                .stream()
                .map(playerState -> evaluatePlayerProfit(playerState, dealerGameResult))
                .toList();
    }

    private ProfitInfo profitInfo(String name, PlayedGameResult dealerGameResult) {
        return new ProfitInfo(name, evaluatePlayerProfit(playerGameStates.get(name), dealerGameResult));
    }

    private Money evaluatePlayerProfit(PlayerState playerState, PlayedGameResult dealerGameResult) {
        return playerState.evaluateProfitWith(dealerGameResult);
    }
}
