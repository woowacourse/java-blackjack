package blackjack.card;

import blackjack.GameRule;

public class AceCard extends Card {

    private static final int SOFT_ACE_VALUE = 11;

    private AceCard(CardSuit suit) {
        super(suit, CardRank.ACE);
    }

    public static Card from(CardSuit suit) {
        return new AceCard(suit);
    }

    public int getPoint(int accumulatedSum) {
        if (GameRule.isBust(accumulatedSum + SOFT_ACE_VALUE)) {
            return rank.getValue();
        }
        return SOFT_ACE_VALUE;
    }
}
