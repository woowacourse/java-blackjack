package model.game;

import model.card.Card;

public interface HitAction {

    boolean isPossibleHit();

    void hit(Card card);
}
