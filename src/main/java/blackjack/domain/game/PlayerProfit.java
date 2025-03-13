package blackjack.domain.game;

import blackjack.domain.user.Player;

public class PlayerProfit {

    private final String nickname;
    private final int profit;

    private PlayerProfit(String nickname, int profit) {
        this.nickname = nickname;
        this.profit = profit;
    }

    public static PlayerProfit createWhenPlayerBlackjackWithInitialCard(Player player) {
        ProfitRate rate = ProfitRate.BLACKJACK_WITH_INITIAL_HAND;
        int profit = rate.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public static PlayerProfit createWhenDealerBust(Player player) {
        ProfitRate rate = ProfitRate.WIN;
        int profit = rate.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public static PlayerProfit createWhenPlayerBust(Player player) {
        ProfitRate rate = ProfitRate.LOSE;
        int profit = rate.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public static PlayerProfit createByWinningType(Player player, WinningType winningType) {
        ProfitRate profitRate = winningType.getProfitRate();
        int profit = profitRate.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public String getNickname() {
        return nickname;
    }

    public int getProfit() {
        return profit;
    }
}
