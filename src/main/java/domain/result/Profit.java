package domain.result;

import domain.participant.Player;

public class Profit {

    private final Double profit;

    private Profit(Double profit) {
        this.profit = profit;
    }

    public static Profit winnerProfit(Player player) {
        if (player.isBlackjack()) {
            Double blackJackProfit = player.getMoney() * 1.5;
            return new Profit(blackJackProfit);
        }
        return new Profit((double) player.getMoney());
    }

    public static Profit looserProfit(Player player) {
        return new Profit(-(double) player.getMoney());
    }

    public static Profit tieProfit() {
        return new Profit((double) 0);
    }

    public static Profit dealerProfit(double dealerProfit) {
        return new Profit(dealerProfit);
    }

    public Double getProfit() {
        return profit;
    }
}
