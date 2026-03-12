package model;

import java.util.ArrayList;
import java.util.List;
import view.OutputView;

public class BettingResult {

    private final int BLACK_JACK = 21;
    private final double BLACK_JACK_WIN_PRICE = 0.5;
    private final int GAME_WIN_PRICE = 2;
    private List<Participant> bettingResults;

    public BettingResult() {
        bettingResults = new ArrayList<>();
    }

    public void calculateBettingMoney(Dealer dealer, List<Player> players) {
        int dealerScore = dealer.calculateTotalScore();
        bettingResults.add(dealer);
        players.forEach(player -> {
            resolveBettingResult(dealerScore, player, dealer);
            bettingResults.add(player);
        });
    }

    public void printBettingResult() {
        OutputView.printBettingResultHeader();
        OutputView.printBettingResult(bettingResults);
    }

    private void resolveBettingResult(int dealerScore, Player player, Dealer dealer) {
        int playerScore = player.calculateTotalScore();
        if (player.getIsBlackJack() && !dealer.getIsBlackJack()) {
            updateProfit(dealer, player, false, true);
        } else if (!player.getIsBlackJack() && dealer.getIsBlackJack()) {
            updateProfit(dealer, player, true, false);
        } else if (playerScore > BLACK_JACK) {
            updateProfit(dealer, player, true, false);
        } else if (playerScore < dealerScore) {
            updateProfit(dealer, player, true, false);
        } else if (playerScore > dealerScore) {
            updateProfit(dealer, player, false, false);
        }
    }

    private void updateProfit(Dealer dealer, Player player, boolean dealerWin, boolean blackJack) {
        long money = player.getMoney();
        if (blackJack) {
            player.addProfit((int) (money * BLACK_JACK_WIN_PRICE));
            dealer.subtractProfit((int) (money * BLACK_JACK_WIN_PRICE));
            return;
        } else if (dealerWin) {
            player.subtractProfit(money);
            dealer.addProfit(money);
            return;
        }
        player.addProfit(money);
        dealer.subtractProfit(money);
    }

}
