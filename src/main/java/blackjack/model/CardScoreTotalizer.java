package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import java.util.List;

public class CardScoreTotalizer {

    public static final int ACE_ADVANTAGE = 10;
    public static final int MAX_SCORE_FOR_ACE_ADVANTAGE = 10;

    public static int sum(List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            score += addCardNumberTo(score, card);
        }
        return score;
    }

    private static int addCardNumberTo(int score, Card card) {
        if (isAce(card) && canSumAceAdvantageTo(score)) {
            return card.getNumber() + ACE_ADVANTAGE;
        }
        return card.getNumber();
    }

    private static boolean canSumAceAdvantageTo(int score) {
        return score <= MAX_SCORE_FOR_ACE_ADVANTAGE;
    }

    private static boolean isAce(Card card) {
        return card.hasSameNumber(TrumpNumber.ACE);
    }
}
