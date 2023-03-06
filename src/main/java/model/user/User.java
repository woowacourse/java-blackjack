package model.user;

import model.card.Card;

import java.util.Objects;

public class User {

    public static final int BUST_NUMBER = 21;

    private final String name;
    private final Hand hand;

    public User(final String name) {
        this.name = name;
        this.hand = Hand.create();
    }

    public void receiveCard(final Card card) {
        this.hand.receiveCard(card);
    }

    public int calculateTotalValue() {
        return hand.getTotalValue();
    }

    public boolean judgeResult(int dealerTotalValue) {
        final int playerTotalValue = calculateTotalValue();

        if (dealerTotalValue > BUST_NUMBER || playerTotalValue > BUST_NUMBER) {
            return judgeOverBurst(dealerTotalValue, playerTotalValue);
        }

        return judgeUnderBurst(dealerTotalValue, playerTotalValue);
    }

    private boolean judgeOverBurst(final int dealerTotalValue, final int userTotalValue) {
        if (userTotalValue > BUST_NUMBER && dealerTotalValue > BUST_NUMBER) {
            return Boolean.TRUE;
        }

        if (userTotalValue > BUST_NUMBER) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private Boolean judgeUnderBurst(final int dealerTotalValue, final int userTotalValue) {
        if (dealerTotalValue <= userTotalValue) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(hand, user.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }

    public String getName() {
        return this.name;
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getCardTotalValue() {
        return hand.getTotalValue();
    }
}
