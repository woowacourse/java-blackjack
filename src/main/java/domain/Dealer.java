package domain;

import domain.card.Deck;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Participant;

public class Dealer {

    private static final int STAND_SCORE = 17;

    private final Participant dealer;

    public Dealer(Participant dealer) {
        this.dealer = dealer;
    }

    public Dealer(Deck deck) {
        this.dealer = new Participant(new Name("딜러"), new Hand());
        dealer.initHand(deck);
    }

    public void playTurn(Deck deck) {
        dealer.playTurn(deck);
        if (isStand()) {
            dealer.changeState();
        }
    }

    private boolean isStand() {
        return dealer.getScore() >= STAND_SCORE;
    }

    public boolean isHit() {
        return dealer.isHit();
    }

    public int getScore() {
        return dealer.getScore();
    }

    public Participant getDealer() {
        return dealer;
    }

}
