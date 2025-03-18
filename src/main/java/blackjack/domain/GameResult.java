package blackjack.domain;

import blackjack.domain.card.BlackjackScore;
import blackjack.domain.card.Result;
import blackjack.domain.card.WinningResult;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.HashMap;
import java.util.Map;

public record GameResult(Map<Player, Result> playerResults, Result dealerResults) {

    public static GameResult create(Dealer dealer, Players players) {
        Map<Player, Result> playerResults = createPlayerGameResult(dealer, players);
        Result dealerResults = createDealerGameResults(dealer, players);
        return new GameResult(playerResults, dealerResults);
    }

    private static Map<Player, Result> createPlayerGameResult(Dealer dealer, Players players) {
        Map<Player, Result> playerResults = new HashMap<>();

        players.sendAll((player -> {
            BlackjackScore playerScore = player.getCards().calculateScore();
            BlackjackScore dealerScore = dealer.getCards().calculateScore();

            WinningResult playerWinningResult = playerScore.decide(dealerScore);
            Map<WinningResult, Integer> playerResultIntegerMap = Map.of(playerWinningResult, 1);

            double playerBettingResult = getMultiplyRatio(playerScore, dealerScore);
            int playerProfit = (int) player.makeProfit(playerBettingResult);

            Result playerResult = new Result(playerResultIntegerMap, playerProfit);

            playerResults.put(player, playerResult);
        }));
        return playerResults;
    }

    private static Result createDealerGameResults(Dealer dealer, Players players) {
        Map<WinningResult, Integer> dealerResultIntegerMap = new HashMap<>();
        players.sendAll((player -> {
            BlackjackScore dealerScore = dealer.getCards().calculateScore();
            BlackjackScore playerScore = player.getCards().calculateScore();

            WinningResult dealerWinningResult = dealerScore.decide(playerScore);
            dealerResultIntegerMap.put(
                    dealerWinningResult,
                    dealerResultIntegerMap.getOrDefault(dealerWinningResult, 0) + 1);

            double playerBettingResult = getMultiplyRatio(playerScore, dealerScore);
            dealer.updateBettingMoney(player.makeProfit(playerBettingResult) * -1);
        }));
        return new Result(dealerResultIntegerMap, (int) dealer.getBettingMoney());
    }

    public static double getMultiplyRatio(BlackjackScore playerScore, BlackjackScore dealerScore) {
        WinningResult winningResult = playerScore.decide(dealerScore);
        return winningResult.getProfit();
    }
}
