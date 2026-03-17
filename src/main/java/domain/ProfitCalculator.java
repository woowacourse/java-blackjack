package domain;

import domain.participant.BettingMoney;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class ProfitCalculator {

    public static double calculatePlayerProfit(Player player, Dealer dealer) {
        BettingMoney bettingMoney = player.getBettingMoney();

        if (isDrawBlackjack(player, dealer)) {
            return 0;
        }
        if (player.isBlackjack()) {
            return bettingMoney.blackjackProfit();
        }
        if (isLose(player, dealer)) {
            return bettingMoney.loseProfit();
        }
        if (isWin(player, dealer)) {
            return bettingMoney.winProfit();
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

    private static boolean isDrawBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private static boolean isLose(Player player, Dealer dealer) {
        return dealer.isBlackjack()
                || player.isBust()
                || isDealerHigher(player, dealer);
    }

    private static boolean isWin(Player player, Dealer dealer) {
        return dealer.isBust()
                || isPlayerHigher(player, dealer);
    }

    private static boolean isDealerHigher(Player player, Dealer dealer) {
        return player.getScoreValue() < dealer.getScoreValue() && !dealer.isBust();
    }

    private static boolean isPlayerHigher(Player player, Dealer dealer) {
        return player.getScoreValue() > dealer.getScoreValue();
    }
}
