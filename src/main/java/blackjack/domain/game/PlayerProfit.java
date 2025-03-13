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
        int profit = (int) (player.getBettingAmount() * 1.5);
        return new PlayerProfit(player.getNickname(), profit);
    }

    public static PlayerProfit createWhenDealerBust(Player player) {
        return new PlayerProfit(player.getNickname(), player.getBettingAmount());
    }

    public static PlayerProfit createPlayerBust(Player player) {
        return new PlayerProfit(player.getNickname(), player.getBettingAmount() * -1);
    }

    public static PlayerProfit createByWinningType(Player player, WinningType winningType) {
        int profit = player.getBettingAmount() * winningType.getProfitRate();
        return new PlayerProfit(player.getNickname(), profit);
    }

    public String getNickname() {
        return nickname;
    }

    public int getProfit() {
        return profit;
    }
}
