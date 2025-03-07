package domain;

import java.util.List;

public interface Participant {

    void setUpCardDeck(Card first, Card second);

    boolean canTakeMoreCard();

    void takeMoreCard(Card card);

    int calculateScore();

    List<Card> getCards();
}
