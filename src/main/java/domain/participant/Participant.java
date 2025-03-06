package domain.participant;

import domain.card.Card;

public interface Participant {

    void receive(Card card);

    int getScore();

    int getCardCount();
}
