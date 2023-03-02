package blackjack.domain;

import java.util.List;

public interface Player {
    String getName();

    void initialDraw(final Deck deck);

    boolean canDraw();

    List<String> getCardLetters();
}
