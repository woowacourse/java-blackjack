package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.List;

public interface Player {
    void drawCard(final Card card);

    boolean isCanDraw();

    void drawTwoCards();

    Name getName();

    List<Card> getCards();

    int getValue();

    void draw();

    boolean isDrawStop();

    void stopDraw();
}
