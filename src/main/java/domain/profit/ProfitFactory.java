package domain.profit;

import domain.CardCalculator;
import domain.player.Dealer;
import domain.player.Player;

public class ProfitFactory {
    private ProfitFactory() {
    }

    public static ProfitStrategy create(Player player, Dealer dealer) {
        if (player == null || dealer == null) {
            throw new NullPointerException("플레이어 또는 딜러를 입력하지 않았습니다.");
        }

        if (player.isBlackJack() && dealer.isBlackJack()) {
            return new BothBlackJack();
        }
        if (player.isBlackJack()) {
            return new PlayerBlackJack();
        }
        if (CardCalculator.determineWinner(player.getCard(), dealer.getCard())) {
            return new PlayerWin();
        }
        return new PlayerLoose();
    }
}
