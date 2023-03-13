package model.user;

import model.card.Card;
import model.card.Deck;

public class Dealer {

    private static final int CAN_RECEIVE_DEALER_MAX_NUMBER = 16;

    private final User user;

    public Dealer() {
        final Name dealerName = new Name("딜러");
        this.user = new User(dealerName);
    }

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

    public boolean isBlackJack() {
        return user.isBlackJack();
    }

    public String getName() {
        final Name name = user.getName();
        return name.getName();
    }

    public Hand getHand() {
        return user.getHand();
    }
}
