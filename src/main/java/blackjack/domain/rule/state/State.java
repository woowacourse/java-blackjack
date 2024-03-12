package blackjack.domain.rule.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;

public interface State {

    default boolean isBurst() {
        return false;
    }
    default boolean isBlackjack() {
        return false;
    }
    default boolean isHit() {
        return false;
    }

    default boolean isStand() {
        return false;
    }


    int START_CARD_COUNT = 2;
    String ERROR_MESSAGE = "사용할 수 없는 기능입니다.";

    State draw(Card card);

    State stand();

    Hands getHands();

    int countHit();
}
