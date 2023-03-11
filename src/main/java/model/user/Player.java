package model.user;

import static model.user.GameState.BLACKJACK;
import static model.user.GameState.LOSE;
import static model.user.GameState.TIE;
import static model.user.GameState.WIN;

import model.card.Card;
import model.card.Deck;

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

    public GameState judgeResult(final Dealer dealer) {
        if (isBust() || dealer.isBust()) {
            return judgeBustState(dealer);
        }

        if (isBlackJack() || dealer.isBlackJack()) {
            return judgeBlackJack(dealer);
        }

        return judgeState(dealer.calculateTotalValue());
    }

    private GameState judgeBustState(final Dealer dealer) {
        if (isBust() && dealer.isBust()) {
            return LOSE;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        return LOSE;
    }

    private boolean isBust() {
        return user.isBust();
    }

    private GameState judgeBlackJack(final Dealer dealer) {
        if (isBlackJack() && dealer.isBlackJack()) {
            return TIE;
        }

        if (isBlackJack()) {
            return BLACKJACK;
        }

        return LOSE;
    }

    private GameState judgeState(final int dealerTotalValue) {
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
