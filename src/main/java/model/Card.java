package model;

public class Card {

    private Suits suits;
    private CardNumber cardNumber;

    private Card(Suits suits, CardNumber cardNumber) {
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
        return cardNumber.getName() +  suits.name;
    }
}
