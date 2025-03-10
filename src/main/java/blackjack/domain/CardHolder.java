package blackjack.domain;

import java.util.List;

public interface CardHolder {

    List<Card> getAllCards();

    void takeCard(Card newCard);

    int getOptimisticValue();

    Card getCard(int position);

    boolean isBusted();

    boolean canTakeCardWithin(int takeBoundary);
}
