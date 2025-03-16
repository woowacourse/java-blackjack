package blackjack.domain;

import java.util.List;

public class Dealer implements Participant {

    private static final int DEALER_HIT_BOUNDARY = 16;
    private static final int FIRST_CARD_POSITION = 0;

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    @Override
    public void hit(Card newCard) {
        hand.hit(newCard);
    }

    @Override
    public boolean canHit() {
        return hand.canHitWithin(DEALER_HIT_BOUNDARY);
    }

    @Override
    public boolean isBusted() {
        return hand.isBusted();
    }

    @Override
    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    @Override
    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    @Override
    public int getBestCardValue() {
        return hand.getBestCardValue();
    }

    public Card revealFirstCard() {
        return hand.getCard(FIRST_CARD_POSITION);
    }
}
