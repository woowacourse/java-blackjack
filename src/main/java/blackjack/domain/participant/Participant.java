package blackjack.domain.participant;

import blackjack.domain.Deck;

public interface Participant {

    boolean canReceiveCard();

    void draw(Deck deck);
}
