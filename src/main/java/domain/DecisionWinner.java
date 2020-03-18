package domain;

import domain.card.CardCalculator;
import domain.player.User;

public class DecisionWinner {

    private static final int BLACK_JACK = 21;

    private DecisionWinner() {
    }

    public static boolean compareWinner(User targetUser, User user) {
        int playerCardSum = CardCalculator.sumCardDeck(targetUser.getCard());
        int dealerCardSum = CardCalculator.sumCardDeck(user.getCard());

        return determineWin(playerCardSum, dealerCardSum);
    }

    private static boolean determineWin(int TargetPlayerCardSum, int playerCardSum) {
        if (TargetPlayerCardSum < BLACK_JACK && playerCardSum > BLACK_JACK) {
            return true;
        }
        if (TargetPlayerCardSum > BLACK_JACK) {
            return false;
        }
        return TargetPlayerCardSum >= playerCardSum;
    }
}
