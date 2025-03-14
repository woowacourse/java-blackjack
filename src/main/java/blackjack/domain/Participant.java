package blackjack.domain;

import java.util.List;

public interface Participant {

    void takeCard(Card newCard);

    boolean canTakeCard();

    boolean isBusted();

    boolean isBlackjack();

    List<Card> getAllCards();

    int getBestCardValue();
}
