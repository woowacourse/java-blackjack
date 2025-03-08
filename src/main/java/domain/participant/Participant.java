package domain.participant;

import domain.card.CardDeck;

public interface Participant {
    void hitCards(final CardDeck standard);
    int sum();
}
