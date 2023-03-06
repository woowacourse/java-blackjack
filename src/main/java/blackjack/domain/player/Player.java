package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public interface Player {

    void draw(final Deck deck);

    boolean isDrawable();

    boolean isDealer();

    int calculateScore();

    void stay();

    Result play(final Hand hand);

    String getName();

    List<String> getCardLetters();
}
