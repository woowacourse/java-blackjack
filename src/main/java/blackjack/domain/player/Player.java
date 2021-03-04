package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public interface Player {
    void drawCard(final Card card);

    boolean isCanDraw();

    void drawRandomTwoCards();

    Name getName();

    List<Card> getCards();

    int getValue();

    void drawRandomOneCard();
}
