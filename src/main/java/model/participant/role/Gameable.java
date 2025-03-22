package model.participant.role;

import java.util.List;
import model.deck.Card;
import model.hand.Score;

public interface Gameable {
    void receiveCard(final Card card);

    boolean isBurst();

    boolean isBlackjack();

    boolean canHit();

    Score calculateFinalScore();

    List<Card> openInitialDeal();

    List<Card> getHandCards();
}
