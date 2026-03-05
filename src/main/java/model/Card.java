package model;

public class Card {

    private Suits suits;
    private CardNumber cardNumber;

    public Card(Suits suits, CardNumber cardNumber) {
        this.suits = suits;
        this.cardNumber = cardNumber;
    }

    public static Card of(String suits, int cardNumber) {
        return new Card(Suits.findByName(suits), CardNumber.findByValue(cardNumber));
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
