package model;

import java.util.List;

public class BettingResult {

    private static final int BLACK_JACK = 21;
    private static final double BLACK_JACK_WIN_PRICE = 1.5;
    private static final int GAME_WIN_PRICE = 2;

    private BettingResult() {
    }

    public static void calculateBettingMoney(Dealer dealer, List<Player> players) {
        int dealerScore = dealer.calculateTotalScore();
        players.forEach(player -> {
            resolveBettingResult(dealerScore, player, dealer);
        });
    }

    private static void resolveBettingResult(int dealerScore, Player player, Dealer dealer) {
        int playerScore = player.calculateTotalScore();
        if (playerScore == BLACK_JACK && dealerScore != BLACK_JACK) {
            updateMoney(dealer, player, false, true);
        } else if (playerScore > BLACK_JACK) {
            updateMoney(dealer, player, true, false);
        } else if (playerScore < dealerScore) {
            updateMoney(dealer, player, true, false);
        }
    }

    private static void updateMoney(Dealer dealer, Player player, boolean dealerWin, boolean blackJack) {
        int money = player.getMoney();
        if (!dealerWin && blackJack) {
            player.setMoney((int) (money * BLACK_JACK_WIN_PRICE));
            return;
        } else if (dealerWin) {
            dealer.addDealerProfit(money);
            player.setMoney(0);
            return;
        }
        player.setMoney(money * GAME_WIN_PRICE);
    }
}
