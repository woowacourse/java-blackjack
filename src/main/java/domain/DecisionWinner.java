package domain;

import domain.card.CardCalculator;
import domain.player.User;

public class DecisionWinner {

    private static final int WINNING_COUNT = 21;

    private DecisionWinner() {
    }

    public static boolean compareWinner(User targetUser, User user) {
        int playerCardSum = CardCalculator.sumCardDeck(targetUser.getCard());
        int dealerCardSum = CardCalculator.sumCardDeck(user.getCard());

        return determineWin(playerCardSum, dealerCardSum);
    }

    private static boolean determineWin(int targetUserCardSum, int userCardSum) {
        if (targetUserCardSum < WINNING_COUNT && userCardSum > WINNING_COUNT) {
            return true;
        }
        if (targetUserCardSum > WINNING_COUNT) {
            return false;
        }
        return targetUserCardSum >= userCardSum;
    }
}
