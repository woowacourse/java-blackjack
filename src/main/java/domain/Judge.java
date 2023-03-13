package domain;

public class Judge {

    public static PlayerScore judgeScore(Player player, Dealer dealer) {
        if (dealer.isBurst()) {
            return new PlayerScore(player.getName(), Profit.winnerProfit(player));
        }
        if (player.isBurst()) {
            return new PlayerScore(player.getName(), Profit.looserProfit(player));
        }
        if (dealer.getSumOfCards() > player.getSumOfCards()) {
            return new PlayerScore(player.getName(), Profit.looserProfit(player));
        }
        if (dealer.getSumOfCards() < player.getSumOfCards()) {
            return new PlayerScore(player.getName(), Profit.winnerProfit(player));
        }
        return new PlayerScore(player.getName(), Profit.tieProfit());
    }
}
