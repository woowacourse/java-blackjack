package model;

public class Card {
    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of(String suits, int cardNumber) {
        return new Card(Suit.findByName(suits), Rank.findByValue(cardNumber));
    }

    public static Card of(String suits, String cardName) {
        return new Card(Suit.findByName(suits), Rank.findByName(cardName));
    }

    public static Card of(Suit suit, Rank rank) {
        return new Card(suit, rank);
    }

    public Rank getCardNumber() {
        return rank;
    }

    @Override
    public String toString() {
        return rank.getName() + suit.getName();
    }
}
