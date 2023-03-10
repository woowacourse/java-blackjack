package model.user;

import model.card.Card;
import model.card.Deck;

public class Player {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;
    private static final int BLACK_JACK_NUMBER = 21;
    private static final double BLACK_JACK_WEIGHT = 1.5;

    private final User user;
    private long money;

    private Player(final String name, final int money) {
        this.user = new User(name);
        this.money = money;
    }

    public static Player from(String playerName, int money) {
        return new Player(playerName, money);
    }

    public boolean canReceiveCard() {
        Receivable receivable = () -> CAN_RECEIVE_MAX_NUMBER >= getCardTotalValue();
        return receivable.canReceiveCard();
    }

    public int getCardTotalValue() {
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

    public long getMoney() {
        return this.money;
    }

    public void lose() {
        this.money = -this.money;
    }

    public boolean isBlackJack() {
        return getCardTotalValue() == BLACK_JACK_NUMBER;
    }

    public void receiveMoney() {
        this.money *= BLACK_JACK_WEIGHT;
    }

}
