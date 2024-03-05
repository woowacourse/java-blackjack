package domain;

public class Card {

    private final CardNumber cardNumber;

    public Card(int number) {
        this.cardNumber = CardNumber.find(number);
    }
}
