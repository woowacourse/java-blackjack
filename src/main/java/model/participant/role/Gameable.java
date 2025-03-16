package model.participant.role;

import java.util.List;
import model.deck.Card;

public interface Gameable {
    void receiveCard(final Card card);

    boolean isBurst();

    boolean isBlackjack();

    boolean canHit();

    int calculateFinalScore();

    List<Card> openInitialDeal();

    List<Card> getHandCards();
}
