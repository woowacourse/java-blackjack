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
        int profit = GameResultType.WIN_WITH_INITIAL_HAND_BLACKJACK.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public static PlayerProfit createWhenDealerBust(Player player) {
        int profit = GameResultType.WIN.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public static PlayerProfit createWhenPlayerBust(Player player) {
        int profit = GameResultType.LOSE.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public static PlayerProfit createByWinningType(Player player, GameResultType gameResultType) {
        int profit = gameResultType.calculateProfit(player.getBettingAmount());
        return new PlayerProfit(player.getNickname(), profit);
    }

    public String getNickname() {
        return nickname;
    }

    public int getProfit() {
        return profit;
    }
}
