package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.GamePoint;

public interface Player {

    void draw(Card card);
    boolean canReceive();
    GamePoint getGamePoint();

}
