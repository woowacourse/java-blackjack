package blackjack.domain;

import java.util.List;

public interface Player {
    void initialDraw(final Deck deck);

    void draw(final Deck deck);

    boolean isDrawable();

    boolean isDealer();

    String getName();

    List<String> getCardLetters();
}
