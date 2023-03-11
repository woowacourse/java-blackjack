package model.user;

import static model.card.State.*;

import model.card.Card;
import model.card.Deck;
import model.card.State;

public class Player implements Receivable {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;

    private final User user;

    public Player(final String name) {
        this.user = new User(name);
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

    public State judgeResult(Dealer dealer) {
        if (isBlackJack() && dealer.isBlackJack()) {
            return TIE;
        }

        if (isBlackJack()) {
            return BLACKJACK;
        }

        if (dealer.isBlackJack()) {
            return LOSE;
        }

        return judgeState(dealer.calculateTotalValue());
    }

    private State judgeState(int dealerTotalValue) {
        final int playerTotalValue = calculateTotalValue();

        if (playerTotalValue > dealerTotalValue) {
            return WIN;
        }

        if (playerTotalValue == dealerTotalValue) {
            return TIE;
        }
        return LOSE;
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
