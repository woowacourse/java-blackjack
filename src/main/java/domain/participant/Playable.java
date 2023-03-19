package domain.participant;

import domain.card.Card;

public interface Playable {
    void addCard(final Card... cards);

    boolean canDrawCard();

    boolean isBlackJack();

    boolean isBust();

    int calculateScore();

    void resetMoney(final ParticipantMoney initMoney);

}
