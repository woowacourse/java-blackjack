package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public interface Player {
    void drawOneCard(final Card card);

    boolean isCanDraw();

    int getScore();

    void drawRandomOneCard(Cards allCards);

    void drawRandomTwoCards(Cards allCards);

    Name getName();

    List<Card> getCards();

    boolean isBlackJack();

    boolean isBust();
}
