package domain.participant;

import domain.card.Card;
import domain.GamePoint;

public interface Participant {

    void draw(Card card);

    boolean canReceive();

    GamePoint getGamePoint();

}
