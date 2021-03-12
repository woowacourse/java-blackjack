package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public interface Player {
    void drawCard(final Card card);

    Name getName();

    List<Card> getCards();

    int getScore();

    void drawRandomOneCard();

    boolean isBlackjack();

    boolean isBust();

    void stay();

    boolean isFinished();
}