package domain;

import domain.card.Deck;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Participant;

public record Dealer(Participant dealer) {

    private static final int STAND_SCORE = 17;

    public Dealer(Deck deck) {
        this(new Participant(new Name("딜러"), new Hand()));
        dealer.initHand(deck);
    }

    public void playTurn(Deck deck) {
        dealer.playTurn(deck);
    }

    public boolean stay() {
        return dealer.getScore() <= STAND_SCORE;
    }

    public int getScore() {
        return dealer.getScore();
    }

    public Participant getDealer() {
        return dealer;
    }

}
