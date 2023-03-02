package model;

import model.card.Card;

import java.util.Objects;

public abstract class User {

    private final String name;
    private final Hand hand;

    public User(final String name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void receiveCard(final Card card) {
        this.hand.receiveCard(card);
    }

    public int calculateTotalValue() {
        return hand.calculateTotalValue();
    }

    public abstract boolean canReceiveCard();

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

    public String getHandInfo() {
        return this.hand.toString();
    }
}
