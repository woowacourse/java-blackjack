package domain.participant;

import domain.card.Card;

public class Player extends Participant {
    private static final int PLAYER_MAX_HITTABLE_SCORE = 21;

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public boolean canHit() {
        return getTotalCardScore() < PLAYER_MAX_HITTABLE_SCORE;
    }

    @Override
    public void keepCard(Card card) {
        if (canHit()) {
            this.getHand().addCard(card);
        }
    }
}
