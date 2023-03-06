package model.user;

import model.card.Card;
import model.card.Deck;

public class Player implements Receivable {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;

    private final User user;

    public Player(final String name) {
        this.user = new User(name);
    }

    @Override
    public boolean canReceiveCard() {
        return CAN_RECEIVE_MAX_NUMBER >= calculateTotalValue();
    }

    private int calculateTotalValue() {
        return user.getCardTotalValue();
    }

    public Score judgeResult(int dealerTotalValue) {
        return user.judgeResult(dealerTotalValue);
    }

    public void receiveInitialCards(final Deck deck) {
        receiveCard(deck.pick());
        receiveCard(deck.pick());
    }

    public void receiveCard(final Card card) {
        user.receiveCard(card);
    }

    public String getName() {
        return user.getName();
    }

    public Hand getHand() {
        return user.getHand();
    }
}
