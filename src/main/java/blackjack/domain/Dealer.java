package blackjack.domain;

import java.util.List;

public class Dealer implements Participant {

    private static final int DEALER_MUST_TAKE_UNDER = 16;
    private static final int FIRST_CARD_POSITION = 0;

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    @Override
    public List<Card> getAllCards() {
        return hand.getAllCards();
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
    public boolean isBusted() {
        return hand.isBusted();
    }

    @Override
    public boolean canTakeCard() {
        return hand.canTakeCardWithin(DEALER_MUST_TAKE_UNDER);
    }

    @Override
    public boolean isBlackjack() {
        return hand.isBlackJack();
    }

    public Card revealFirstCard() {
        return hand.getCard(FIRST_CARD_POSITION);
    }
}
