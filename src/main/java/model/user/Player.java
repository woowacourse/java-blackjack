package model.user;

import model.card.Card;
import model.card.Deck;

public class Player implements Receivable {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;

    private final User user;

    public Player(final String name) {
        this.user = new User(name);
    }

    public Result judgeResult(int dealerTotalValue) {
        return user.judgeResult(dealerTotalValue);
    }

    public void receiveInitialCards(final Deck deck) {
        user.receiveInitialCards(deck);
    }

    public void receiveCard(final Card card) {
        user.receiveCard(card);
    }

    @Override
    public boolean canReceiveCard() {
        return CAN_RECEIVE_MAX_NUMBER >= calculateTotalValue();
    }

    public int calculateTotalValue() {
        return user.getCardTotalValue();
    }

    public Result judgeResult(Dealer dealer) {
        return judgeResult(dealer.calculateTotalValue());
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
