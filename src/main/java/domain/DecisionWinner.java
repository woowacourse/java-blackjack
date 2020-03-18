package domain;

import domain.card.CardCalculator;
import domain.player.User;

public class DecisionWinner {
    private static final int MAX_WINNING_COUNT = 21;

    private DecisionWinner() {
    }

    public static boolean compareWinner(User targetUser, User user) {
        int targetUserCardSum = CardCalculator.sumCardDeck(targetUser.getCard());
        int userCardSum = CardCalculator.sumCardDeck(user.getCard());

        return determineWin(targetUserCardSum, userCardSum);
    }

    private static boolean determineWin(int targetUserCardSum, int userCardSum) {
        if (targetUserCardSum < MAX_WINNING_COUNT && userCardSum > MAX_WINNING_COUNT) {
            return true;
        }
        if (targetUserCardSum > MAX_WINNING_COUNT) {
            return false;
        }
        return targetUserCardSum >= userCardSum;
    }
}
