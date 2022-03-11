package blackjack;

import blackjack.trumpcard.Card;

public interface PlayerInterface {
    void receiveCard(Card card);

    boolean isBust();

    String getName();
}
