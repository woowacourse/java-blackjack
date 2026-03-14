package domain;

import domain.card.Card;

import java.util.List;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    @Override
    public boolean canHit() {
        return hand.isLessThanBlackJack();
    }
}
