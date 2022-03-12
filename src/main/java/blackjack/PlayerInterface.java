package blackjack;

import blackjack.trumpcard.Card;

public interface PlayerInterface {
    void receive(Card card);

    boolean canReceive();

    String getName();
}
