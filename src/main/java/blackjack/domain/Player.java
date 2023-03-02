package blackjack.domain;

import java.util.List;

public interface Player {

    void draw(Card card);
    boolean canReceive();
    GamePoint getGamePoint();

}
