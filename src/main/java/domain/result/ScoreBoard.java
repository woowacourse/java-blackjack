package domain.result;

import domain.Money;
import domain.ProfitInfo;
import domain.PlayedGameResult;
import java.util.List;

public class ScoreBoard {

    private final PlayerGameStates playerGameStates;

    public ScoreBoard() {
        this.playerGameStates = new PlayerGameStates();
    }

    public void setupPlayerBettingMoney(String name, Money bettingMoney) {
        playerGameStates.setupPlayerBettingMoney(name, bettingMoney);
    }

    public void record(PlayedGameResult playedGameResult) {
        playerGameStates.record(playedGameResult);
    }

    public List<PlayedGameResult> playerGameResults() {
        return playerGameStates.playerGameResults();
    }

    public ProfitInfo evaluateDealerProfitInfo(PlayedGameResult dealerGameResult) {
        return new ProfitInfo(dealerGameResult.name(), evaluateDealerProfit(dealerGameResult));
    }

    public List<ProfitInfo> evaluatePlayerProfitInfosWith(PlayedGameResult dealerGameResult) {
        return playerGameStates.evaluatePlayerProfitInfosWith(dealerGameResult);
    }

    private Money evaluateDealerProfit(PlayedGameResult dealerGameResult) {
        Money playerProfits = playerGameStates.PlayerProfitsSumFrom(dealerGameResult);
        long loss = playerProfits.amount();

        return Money.of(Math.negateExact(loss));
    }
}
