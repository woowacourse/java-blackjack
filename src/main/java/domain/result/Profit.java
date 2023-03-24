package domain.result;

import domain.participant.Player;

public class Profit {

    private final double profit;

    private Profit(double profit) {
        this.profit = profit;
    }

    public static Profit winnerProfit(Player player) {
        if (player.isBlackjack()) {
            return new Profit(player.getMoney() * 1.5);
        }
        return new Profit(player.getMoney());
    }

    public static Profit looserProfit(Player player) {
        return new Profit(-(double) player.getMoney());
    }

    public static Profit tieProfit() {
        return new Profit(0);
    }

    public static Profit dealerProfit(double dealerProfit) {
        return new Profit(dealerProfit);
    }

    public Double getProfit() {
        return profit;
    }
}
