package blackjack.domain;

import java.util.List;

public interface Participant {

    void hit(Card newCard);

    boolean canHit();

    boolean isBusted();

    boolean isBlackjack();

    List<Card> getAllCards();

    int getBestCardValue();
}
