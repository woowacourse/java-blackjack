package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public interface Participant {
    void drawAtFirst(Deck deck);
    String showCardsAtFirst();
    void hit(Card card);
    String getCards();
    String getName();
    int getScore();
}
