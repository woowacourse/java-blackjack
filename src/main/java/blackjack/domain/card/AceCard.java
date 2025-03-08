package blackjack.domain.card;

import blackjack.domain.GameRule;

public class AceCard extends Card {
    private AceCard(CardSuit suit) {
        super(suit, CardRank.ACE);
    }

    public static Card from(CardSuit suit) {
        return new AceCard(suit);
    }

    public int getPoint(int accumulatedSum) {
        if (GameRule.isBust(accumulatedSum + rank.getSoftAceValue())) {
            return rank.getValue();
        }
        return rank.getSoftAceValue();
    }
}
