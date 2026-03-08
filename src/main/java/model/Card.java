package model;

public class Card {
    private final Suits suits;
    private final CardNumber cardNumber;

    public Card(Suits suits, CardNumber cardNumber) {
        this.suits = suits;
        this.cardNumber = cardNumber;
    }

    public static Card of(String suits, int cardNumber) {
        return new Card(Suits.findByName(suits), CardNumber.findByValue(cardNumber));
    }

    public static Card of(String suits, String cardName) {
        return new Card(Suits.findByName(suits), CardNumber.findByName(cardName));
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return cardNumber.getName() + suits.getName();
    }
}
