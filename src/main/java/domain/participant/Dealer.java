package domain.participant;

import domain.card.Deck;

public class Dealer extends Participant {

    private static final int STAND_SCORE = 17;

    public Dealer(Hand hand) {
        super(new Name("딜러"), hand);
    }

    @Override
    public void playTurn(Deck deck) {
        super.playTurn(deck);
        if (isStand()) {
            changeState();
        }
    }

    private boolean isStand() {
        return getScore() >= STAND_SCORE;
    }
}
