package domain.member;

import domain.card.Card;
import java.util.List;

public interface Participant {

    MemberInfo info();

    default String name() {
        return info().name();
    }

    default void receiveCard(Card card) {
        info().receiveCard(card);
    }

    default int currentScore() {
        return info().currentScore();
    }

    default List<Card> currentCards() {
        return info().currentCards();
    }

    default boolean isFinished() {
        return info().isFinished();
    }

    default boolean isBust() {
        return info().isBust();
    }

    default void changeToStay() {
        info().changeToStay();
    }
}
