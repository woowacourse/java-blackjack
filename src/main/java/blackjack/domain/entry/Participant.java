package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

public interface Participant {
    int countCards();

    void putCard(Card card);

    List<Card> openCard();

    String getName();

    HoldCards getHoldCards();
}
