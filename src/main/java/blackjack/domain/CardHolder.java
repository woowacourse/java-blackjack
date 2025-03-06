package blackjack.domain;

import java.util.List;

public interface CardHolder {
    List<Card> getAllCards();

    void takeCard(Card newCard);

    List<Integer> getPossibleSums();
}
