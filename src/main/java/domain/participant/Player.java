package domain.participant;

import domain.Result;
import domain.card.Deck;
import domain.hand.Hand;

public interface Player {

    void drawFrom(Deck deck);

    Result competeWith(Player other);

    boolean canHit();

    Hand getHand();

    String getName();
}
