package blackjack;

import blackjack.trumpcard.TrumpCard;

public interface PlayerInterface {
    void addCard(TrumpCard card);

    boolean isBust();

    void hit(TrumpCard card);

    String getName();
}
