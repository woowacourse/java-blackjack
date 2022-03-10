package blackjack;

import java.util.List;

public interface Participant {
    int countCards();

    void putCard(Card card);

    List<Card> openCard();

    String getName();

    HoldCards getHoldCards();
}
