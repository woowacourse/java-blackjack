package blackjack.domain.card;

import blackjack.domain.GameRule;

public class AceCard extends Card {
    public AceCard(CardSuit suit) {
        super(suit, CardRank.ACE);
    }

    public int getPoint(int accumulatedSum) {
        if (GameRule.isBust(accumulatedSum + rank.getSoftAceValue())) {
            return rank.getValue();
        }
        return rank.getSoftAceValue();
    }
}
