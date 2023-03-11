package model.user;

import model.card.Card;
import model.card.Deck;

public class Dealer implements Receivable {

    private static final int CAN_RECEIVE_DEALER_MAX_NUMBER = 16;

    private final User user;

    public Dealer() {
        this.user = new User("딜러");
    }

    @Override
    public boolean canReceiveCard() {
        return CAN_RECEIVE_DEALER_MAX_NUMBER >= calculateTotalValue();
    }

    public int calculateTotalValue() {
        return user.getCardTotalValue();
    }

    public void receiveInitialCards(final Deck deck) {
        user.receiveInitialCards(deck);
    }

    public void receiveCard(Card card) {
        user.receiveCard(card);
    }

    public boolean isBust() {
        return user.isBust();
    }

    @Override
    public boolean isBlackJack() {
        return user.isBlackJack();
    }

    public String getName() {
        return user.getName();
    }

    public Hand getHand() {
        return user.getHand();
    }
}
