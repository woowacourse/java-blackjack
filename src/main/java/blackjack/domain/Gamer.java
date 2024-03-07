package blackjack.domain;

import blackjack.domain.card.Card;

public interface Gamer {

    void draw(Card card);

    boolean canDraw();
}
