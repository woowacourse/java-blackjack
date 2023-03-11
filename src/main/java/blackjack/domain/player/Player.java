package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public interface Player {

    boolean isDrawable();

    boolean isDealer();

    void initialDraw(Deck deck);

    void draw(final Deck deck);

    int score();

    void stay();

    Result play(final Hand hand);

    boolean isSameName(Name name);

    Name name();

    String getNameValue();

    List<String> getSymbols();
}
