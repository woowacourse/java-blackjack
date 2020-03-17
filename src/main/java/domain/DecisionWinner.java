package domain;

import domain.player.Dealer;
import domain.player.Player;

public class DecisionWinner {
    private DecisionWinner() {
    }

    public static boolean compareWinner(Player player, Dealer dealer) {
        if (player == null || dealer == null) {
            throw new NullPointerException("유저 또는 딜러를 입력하지 않았습니다.");
        }

        return CardCalculator.determineWinner(player.getCard(), dealer.getCard());
    }
}
