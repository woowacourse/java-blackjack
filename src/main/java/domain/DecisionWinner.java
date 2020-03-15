package domain;

import domain.player.Dealer;
import domain.player.User;

public class DecisionWinner {
    private static final int BLACK_JACK = 21;

    private DecisionWinner() {
    }

    public static boolean compareWinner(User user, Dealer dealer) {
        if (user == null || dealer == null) {
            throw new NullPointerException("유저 또는 딜러를 입력하지 않았습니다.");
        }

        int playerCardSum = CardCalculator.calculateContainAce(user.getCard());
        int dealerCardSum = CardCalculator.calculateContainAce(dealer.getCard());

        return determineWin(playerCardSum, dealerCardSum);
    }

    private static boolean determineWin(int playerCardSum, int dealerCardSum) {
        if (playerCardSum < BLACK_JACK && dealerCardSum > BLACK_JACK) {
            return true;
        }
        if (playerCardSum > BLACK_JACK) {
            return false;
        }
        return playerCardSum >= dealerCardSum;
    }
}
