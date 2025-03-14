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
    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    @Override
    public boolean canTakeCard() {
        return hand.canTakeCardWithin(DEALER_MUST_TAKE_UNDER);
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
