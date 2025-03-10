package blackjack.domain;

import java.util.List;

public interface CardHolder {

    List<Card> getAllCards();

    void takeCard(Card newCard);

    int getOptimisticValue();

    boolean isBusted();

    boolean canTakeCard();
}
