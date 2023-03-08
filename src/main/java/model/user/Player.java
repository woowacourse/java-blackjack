package model.user;

import model.card.Card;
import model.card.Deck;

public class Player {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;

    private final User user;

    private Player(final String name, final int money) {
        this.user = new User(name, money);
    }

    public static Player from(String playerName, int money) {
        return new Player(playerName, money);
    }

    public boolean canReceiveCard() {
         Receivable receivable = () -> CAN_RECEIVE_MAX_NUMBER >= calculateTotalValue();
         return receivable.canReceiveCard();
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
        return user.getInventory().getHand();
    }
}
