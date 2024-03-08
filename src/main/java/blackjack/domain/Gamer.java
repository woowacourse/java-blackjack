package blackjack.domain;

import blackjack.domain.card.Card;

public interface Gamer {

    void draw(final Card card);

    boolean canDraw();

    int calculateScore();
}
