package domain.participant;

import domain.card.Card;
import domain.result.GameResult;

public interface Participant {
    void receive(final Card card);

    boolean canReceive();

    GameResult determineBlackjackResult(Participant opponent);

    boolean isBlackjack();

    boolean isBust();

    int calculateScore();
}
