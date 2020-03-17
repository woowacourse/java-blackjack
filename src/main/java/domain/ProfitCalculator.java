package domain;

import domain.player.Dealer;
import domain.player.Player;

public class ProfitCalculator {
    private static final double WIN_BY_BLACK_JACK_RATE = 1.5d;
    private static final double LOOSE_RATE = -1d;

    private ProfitCalculator() {
    }

    public static Double getProfit(Player player, Dealer dealer) {
        if (player == null || dealer == null) {
            throw new NullPointerException("플레이어 또는 딜러를 입력하지 않았습니다.");
        }

        if (player.isBlackJack() && dealer.isBlackJack()) {
            return (double) player.getMoney();
        }
        if (player.isBlackJack()) {
            return (WIN_BY_BLACK_JACK_RATE * (double) player.getMoney());
        }
        if (CardCalculator.determineWinner(player.getCard(), dealer.getCard())) {
            return (double) player.getMoney();
        }
        return (LOOSE_RATE * (double) player.getMoney());
    }
}
