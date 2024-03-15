package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public interface State {

    State draw(Card card);

    State stand();

    Hands hands();

    State copy();

    default boolean isBlackjack() {
        return false;
    }

    default boolean isNotBlackjack() {
        return !isBlackjack();
    }

    default boolean isStand() {
        return false;
    }

    default boolean isHit() {
        return false;
    }

    default boolean isBust() {
        return false;
    }

    default boolean isNotBust() {
        return !isBust();
    }

    default boolean isFinished() {
        return false;
    }

    default boolean isNotFinish() {
        return !isFinished();
    }
}
