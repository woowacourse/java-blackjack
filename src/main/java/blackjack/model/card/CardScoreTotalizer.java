package blackjack.model.card;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import java.util.List;

public class CardScoreTotalizer {

    public static final int ACE_ADVANTAGE = 10;
    public static final int MAX_SCORE_FOR_ACE_ADVANTAGE = 10;

    public static int sum(final List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            score += addCardNumberTo(score, card);
        }
        return score;
    }

    private static int addCardNumberTo(final int score, final Card card) {
        if (isAce(card) && canSumAceAdvantageTo(score)) {
            return card.getNumber() + ACE_ADVANTAGE;
        }
        return card.getNumber();
    }

    private static boolean canSumAceAdvantageTo(final int score) {
        return score <= MAX_SCORE_FOR_ACE_ADVANTAGE;
    }

    private static boolean isAce(final Card card) {
        return card.hasSameNumber(TrumpNumber.ACE);
    }
}
