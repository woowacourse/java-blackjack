package blackjack.domain.player;

import blackjack.domain.card.Card;

public interface Player {
    void drawCard(final Card card);
    boolean isCanDraw();
    int getValue();
}
