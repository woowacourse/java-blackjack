package blackjack.domain;

import blackjack.domain.card.CardHand;

public enum HandState {
    BLACKJACK,
    BUST,
    NORMAL
    ;

    public static HandState from(CardHand cardHand) {
        if(cardHand.isBlackjack()) {
            return BLACKJACK;
        }
        if(cardHand.isBust()) {
            return BUST;
        }
        return NORMAL;
    }
}
