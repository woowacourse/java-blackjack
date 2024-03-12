package blackjack.model.participant;

import blackjack.model.deck.Card;
import java.util.List;

public interface Playable {

    void receiveCard(final Card card);
    List<Card> openCards();
    int getScore();
    int getCardCount();
    boolean canHit();
    boolean isBust();
}
