package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.OutputView;

public class Result {

    public void openResult(Players players, Dealer dealer) {
        openDealerResult(dealer);
        openPlayersResult(players);
    }

    private void openDealerResult(final Dealer dealer) {
        OutputView.printResult(dealer.getName(), dealer.getCards(), dealer.getTotal());
        OutputView.printNewLine();
    }

    private void openPlayersResult(final Players players) {
        for (Player player : players.getPlayers()) {
            OutputView.printResult(player.getName(), player.getCards(), player.getTotal());
            OutputView.printNewLine();
        }
        OutputView.printNewLine();
    }

    public void openProfits(final Betting betAmount, final Dealer dealer, final Players players) {
        GameResult gameResult = new GameResult();
        players.decideWinners(gameResult, dealer);
        OutputView.printProfitTitle();
        printDealerProfit(betAmount, gameResult, dealer);
        printPlayerProfit(betAmount, gameResult, players);
    }

    private void printDealerProfit(final Betting betAmount, final GameResult gameResult, final Dealer dealer) {
        BettingMoney money = betAmount.getDealerProfit(gameResult);
        OutputView.printProfit(dealer.getName(), money.getMoney());
    }

    private void printPlayerProfit(final Betting betAmount, final GameResult gameResult, final Players players) {
        for (Player player : players.getPlayers()) {
            BettingMoney bettingMoney = betAmount.getProfit(gameResult, player);
            OutputView.printProfit(player.getName(), bettingMoney.getMoney());
        }
    }
}
