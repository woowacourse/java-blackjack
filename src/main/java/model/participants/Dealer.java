package model.participants;

import model.cards.Card;
import model.cards.Deck;
import model.cards.Hand;

public class Dealer extends Participant {
    private static final int DEALER_HIT_THRESHOLD = 17;
    private static final int HAND_SIZE = 2;
    private static final int CARD_COUNT = 1;

    private final Deck deck;

    public Dealer() {
        this.deck = new Deck();
    }

    public void initializeDealerWithHand() {
        hand.addCards(deck.drawCards(HAND_SIZE));
    }

    public Hand produceHand() {
        Hand hand = new Hand();
        hand.addCards(deck.drawCards(HAND_SIZE));
        return hand;
    }

    public Card pullCard() {
        return deck.drawCards(CARD_COUNT).getFirst();
    }

    public void updateWager(double multiplier, double playerWager) {
        wager.updateWager(ParticipantType.DEALER, multiplier, playerWager);
    }

    @Override
    public boolean canHit() {
        calculateHandScore();
        return hand.getScore() < DEALER_HIT_THRESHOLD;
    }

    @Override
    public void calculateHandScore() {
        hand.calculateScore(ParticipantType.DEALER);
    }
}
