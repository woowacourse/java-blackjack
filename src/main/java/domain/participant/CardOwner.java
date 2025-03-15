package domain.participant;

import domain.card.Card;

public interface CardOwner {
    void receive(final Card card);

    boolean canReceive();

    int calculateScore();

    int countCard();
}
