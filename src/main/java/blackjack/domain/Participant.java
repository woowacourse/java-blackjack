package blackjack.domain;

import java.util.List;

public interface Participant {

    List<Card> getAllCards();

    void takeCard(Card newCard);

    int getOptimisticValue();

    boolean isBusted();

    boolean canTakeCard();
}
