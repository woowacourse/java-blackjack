package domain;

import domain.player.Dealer;
import domain.player.User;

public class DecisionWinner {
    private DecisionWinner() {
    }

    public static boolean compareWinner(User user, Dealer dealer) {
        if (user == null || dealer == null) {
            throw new NullPointerException("유저 또는 딜러를 입력하지 않았습니다.");
        }

        return CardCalculator.determineWinner(user.getCard(), dealer.getCard());
    }
}
