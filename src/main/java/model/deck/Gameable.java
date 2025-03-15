package model.deck;

import java.util.List;

public interface Gameable {
    void receiveCard(final Card card);

    boolean isBurst();

    boolean isBlackjack();

    boolean canHit();

    int calculateFinalScore();

    List<Card> openInitialDeal();

    List<Card> getHandCards();
}
