package blackjack.domain.player;

import blackjack.domain.game.Result;
import blackjack.domain.card.Deck;

import java.util.List;

public interface Player {
    void initialDraw(final Deck deck);

    void draw(final Deck deck);

    boolean isDrawable();

    boolean isDealer();

    int calculateScore();

    void stay();

    Result play(final Hand hand);

    String getName();

    List<String> getCardLetters();
}
