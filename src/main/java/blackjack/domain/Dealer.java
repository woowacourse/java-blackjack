package blackjack.domain;

import java.util.List;

public class Dealer implements CardHolder {

    private static final int DEALER_MUST_TAKE_UNDER = 16;
    private static final int FIRST_CARD_POSITION = 0;

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    @Override
    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    @Override
    public int getOptimisticValue() {
        return hand.getOptimisticValue();
    }

    @Override
    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    public Card revealFirstCard() {
        return hand.getCard(FIRST_CARD_POSITION);
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public boolean canTakeCard() {
        return hand.canTakeCardWithin(DEALER_MUST_TAKE_UNDER);
    }
}
