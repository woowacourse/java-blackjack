package model.user;

import model.card.Card;
import model.card.Deck;

import java.util.Objects;

public class User {

    private final String name;
    private final Hand hand;

    public User(final String name) {
        this.name = name;
        this.hand = Hand.create();
    }

    public void receiveInitialCards(final Deck deck) {
        receiveCard(deck.pick());
        receiveCard(deck.pick());
    }

    public void receiveCard(final Card card) {
        this.hand.receiveCard(card);
    }

    public int calculateTotalValue() {
        return hand.getTotalValue();
    }

    public Score judgeResult(int dealerTotalValue) {
        final int playerTotalValue = calculateTotalValue();
        return Score.judge(dealerTotalValue, playerTotalValue);
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
