package model;

import java.util.ArrayList;
import java.util.List;
import view.OutputView;

public class BettingResult {

    private static final int BLACK_JACK = 21;
    private static final double BLACK_JACK_WIN_PRICE = 1.5;
    private static final double BLACK_JACK_DEALER_LOSS = 0.5;
    private static final int GAME_WIN_PRICE = 2;
    private static List<Participant> bettingResults = new ArrayList<>();

    public BettingResult() {
    }

    public static void calculateBettingMoney(Dealer dealer, List<Player> players) {
        int dealerScore = dealer.calculateTotalScore();
        bettingResults.add(dealer);
        players.forEach(player -> {
            resolveBettingResult(dealerScore, player, dealer);
            bettingResults.add(player);
        });
    }

    private static void resolveBettingResult(int dealerScore, Player player, Dealer dealer) {
        int playerScore = player.calculateTotalScore();
        if (player.getIsBlackJack() && !dealer.getIsBlackJack()) {
            updateMoney(dealer, player, false, true);
        } else if (!player.getIsBlackJack() && dealer.getIsBlackJack()) {
            updateMoney(dealer, player, true, false);
        } else if (playerScore > BLACK_JACK) {
            updateMoney(dealer, player, true, false);
        } else if (playerScore < dealerScore) {
            updateMoney(dealer, player, true, false);
        } else if (playerScore > dealerScore) {
            updateMoney(dealer, player, false, false);
        }
    }

    private static void updateMoney(Dealer dealer, Player player, boolean dealerWin, boolean blackJack) {
        int money = player.getMoney();
        if (blackJack) {
            player.setMoney((int) (money * BLACK_JACK_WIN_PRICE));
            dealer.subtractDealerProfit((int) (money * BLACK_JACK_DEALER_LOSS));
            return;
        } else if (dealerWin) {
            dealer.addDealerProfit(money);
            player.setMoney(-money);
            return;
        }
        player.setMoney(money * GAME_WIN_PRICE);
        dealer.subtractDealerProfit(money);
    }

    public static void printBettingResult() {
        OutputView.printBettingResultHeader();
        OutputView.printBettingResult(bettingResults);
    }
}
