package blackjack.model;

public class Card {

    private final CardPattern cardPattern;
    private final CardNumber cardNumber;

    public Card(CardPattern cardPattern, CardNumber cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }
}
