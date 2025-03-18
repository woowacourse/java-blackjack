package domain.state;

import domain.card.Card;
import domain.card.Cards;

public interface State {

    State hit(Card card);

    State stay();

    boolean isFinished();

    Cards cards();

    StateType type();

    enum StateType {
        BLACKJACK,
        STAY,
        BUST,
        HITTABLE,
        ;

        public boolean isBlackjack() {
            return this == BLACKJACK;
        }

        public boolean isBust() {
            return this == BUST;
        }
    }

}
