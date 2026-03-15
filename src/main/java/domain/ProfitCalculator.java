package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class ProfitCalculator {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    public static double calculatePlayerProfit(Player player, Dealer dealer) {
        int bettingMoney = player.getBettingMoneyValue();
        int playerScore = player.getScoreValue();
        int dealerScore = dealer.getScoreValue();

        if (player.isBlackjack() && dealer.isBlackjack()) {
            return 0;
        }
        if (player.isBlackjack()) {
            return bettingMoney * BLACKJACK_PROFIT_RATE;
        }
        if (dealer.isBlackjack()) {
            return -bettingMoney;
        }
        if (player.isBust()) {
            return -bettingMoney;
        }
        if (dealer.isBust()) {
            return bettingMoney;
        }
        if (playerScore > dealerScore) {
            return bettingMoney;
        }
        if (playerScore < dealerScore) {
            return -bettingMoney;
        }
        return 0;
    }

    public static double calculateDealerProfit(Players players, Dealer dealer) {
        return -players.getPlayers().stream()
                .mapToDouble(player -> calculatePlayerProfit(player, dealer))
                .sum();
    }

    public static String formatProfit(double profit) {
        if (profit == (long) profit) {
            return String.valueOf((long) profit);
        }
        return String.valueOf(profit);
    }
}
