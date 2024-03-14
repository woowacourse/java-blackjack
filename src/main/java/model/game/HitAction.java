package model.game;

import model.card.Card;

public interface HitAction {

    boolean isPossibleHit();

    void hitCard(Card card);
}
