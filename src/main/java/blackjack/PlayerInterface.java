package blackjack;

import java.util.List;

public interface PlayerInterface {
    void addCard(TrumpCard card);

    boolean isBust();

    void hit(TrumpCard card);

    String getName();

    List<String> getDeckToString();
}
