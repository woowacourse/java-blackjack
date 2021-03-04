package blackjack.domain;

import java.util.List;

public interface Playable {
    String getName();

    List<Card> getUnmodifiableCards();

    void takeCard(Card pop);

    int sumCards();

    int sumCardsForResult();

    boolean isAvailableToTake();

    int result(int i);
}
