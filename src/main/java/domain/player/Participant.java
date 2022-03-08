package domain.player;

import domain.card.Card;

public interface Participant {
    boolean isFinished();
    void drawCard(Card card);
}
