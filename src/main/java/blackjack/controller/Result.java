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

    public void openProfits(final Bank bank, final Dealer dealer, final Players players) {
        GameResult gameResult = new GameResult();
        players.determine(gameResult, dealer);
        OutputView.printProfitTitle();
        printDealerProfit(bank, gameResult, dealer);
        printPlayerProfit(bank, gameResult, players);
    }

    private void printDealerProfit(final Bank bank, final GameResult gameResult, final Dealer dealer) {
        Money money = bank.getDealerProfit(gameResult);
        OutputView.printProfit(dealer.getName(), money.getMoney());
    }

    private void printPlayerProfit(final Bank bank, final GameResult gameResult, final Players players) {
        for (Player player : players.getPlayers()) {
            Money money = bank.getProfit(gameResult, player);
            OutputView.printProfit(player.getName(), money.getMoney());
        }
    }
}
