package domain.user;

import domain.card.Card;
import domain.GamePoint;

public interface Player {

    void draw(Card card);

    boolean canReceive();

    GamePoint getGamePoint();

}
