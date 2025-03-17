package blackjack.domain;

import blackjack.domain.card.BlackjackScore;
import blackjack.domain.card.Result;
import blackjack.domain.card.WinningResult;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.HashMap;
import java.util.Map;

public record GameResult(Map<Player, Result> playerResults, Map<WinningResult, Integer> dealerResults) {
    private static int dealerBettingResult;

    public static GameResult create(Dealer dealer, Players players) {
        Map<Player, Result> playerResults = createPlayerGameResult(dealer, players);
        Map<WinningResult, Integer> dealerResults = createDealerGameResults(dealer, players);
        return new GameResult(playerResults, dealerResults);
    }

    private static Map<Player, Result> createPlayerGameResult(Dealer dealer, Players players) {
        Map<Player, Result> playerResults = new HashMap<>();

        players.sendAll((player -> {
            BlackjackScore playerScore = player.getCards().calculateScore();
            BlackjackScore dealerScore = dealer.getCards().calculateScore();
            WinningResult playerWinningResult = playerScore.decide(dealerScore);
            int playerBettingResult = getMultiplyRatio(playerScore, dealerScore);
            int playerProfit = player.makeProfit(playerBettingResult);
            Result playerResult = new Result(playerWinningResult, playerProfit);

            playerResults.put(player, playerResult);
        }));
        return playerResults;
    }

    private static Map<WinningResult, Integer> createDealerGameResults(Dealer dealer, Players players) {
        Map<WinningResult, Integer> dealerGameResults = new HashMap<>();
        players.sendAll((player -> {
            BlackjackScore dealerScore = dealer.getCards().calculateScore();
            BlackjackScore playerScore = player.getCards().calculateScore();
            WinningResult dealerWinningResult = dealerScore.decide(playerScore);
            dealerGameResults.put(
                    dealerWinningResult,
                    dealerGameResults.getOrDefault(dealerWinningResult, 0) + 1);
            int playerBettingResult = getMultiplyRatio(playerScore, dealerScore);
            int dealerProfit = player.makeProfit(playerBettingResult) * -1;
            dealerBettingResult += dealerProfit;
        }));
        return dealerGameResults;
    }

    public static int getMultiplyRatio(BlackjackScore playerScore, BlackjackScore dealerScore) {
        WinningResult winningResult = playerScore.decide(dealerScore);
        if (winningResult.equals(WinningResult.WIN)) {
            return 1;
        }
        if (winningResult.equals(WinningResult.LOSE)) {
            return -1;
        }
        return 0;
    }

    public int getDealerBettingResult() {
        return dealerBettingResult;
    }
}
