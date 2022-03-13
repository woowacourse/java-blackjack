package blackjack.model.player;

import blackjack.model.trumpcard.Deck;
import blackjack.model.trumpcard.TrumpCard;
import java.util.List;

public interface PlayerInterface {
    void addCard(TrumpCard card);

    boolean isBust();

    void hit(TrumpCard card);

    String getName();

    Deck getDeck();
}
