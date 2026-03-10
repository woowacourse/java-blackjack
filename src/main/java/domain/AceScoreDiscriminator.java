package domain;

import java.util.List;

public class AceScoreDiscriminator {
    private static final int BUST_CRITERIA = 21;

    public static int calculateAceCardsSum(List<Card> cards, int sumExceptAce) {
        int aceCount = countAce(cards);

        int aceSum = 0;
        for (int i = 0; i < aceCount; i++) {
            int leftAceCount = aceCount - (1 + i);
            if ((sumExceptAce + aceSum) + 11 + leftAceCount <= BUST_CRITERIA) {
                aceSum += 11;
            } else {
                aceSum += 1;
            }
        }
        return aceSum;
    }

    private static int countAce(List<Card> cards) {
        int count = 0;
        for (Card card : cards) {
            if (card.isAce()) {
                count++;
            }
        }
        return count;
    }
}
