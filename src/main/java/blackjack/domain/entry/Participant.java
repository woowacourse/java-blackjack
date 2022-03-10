package blackjack.domain.entry;

import blackjack.domain.card.HoldCards;
import blackjack.domain.card.Card;

import java.util.List;

public interface Participant {
    int countCards();

    void putCard(Card card);

    List<Card> openCard();

    String getName();

    HoldCards getHoldCards();
}
