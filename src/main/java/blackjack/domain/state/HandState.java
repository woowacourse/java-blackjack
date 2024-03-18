package blackjack.domain.state;

import blackjack.domain.card.CardHand;

import java.util.function.Predicate;

public enum HandState {
    BUST(cardHand -> cardHand.calculateScore() > 21),
    BLACKJACK(cardHand -> cardHand.calculateScore() == 21 && cardHand.size() == 2),
    NOT_BUST(cardHand -> cardHand.calculateScore() <= 21 && !BLACKJACK.matches(cardHand));

    HandState(Predicate<CardHand> condition) {
        this.condition = condition;
    }

    private final Predicate<CardHand> condition;

    public boolean matches(CardHand cardHand) {
        return condition.test(cardHand);
    }
}
