package domain.card;

public class Card {

    private final CardPattern cardPattern;
    private final CardNumber cardNumber;

    private Card(final CardPattern cardPattern, final CardNumber cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }

    public static Card create(final CardPattern cardPattern, final CardNumber cardNumber) {
        return new Card(cardPattern, cardNumber);
    }
}
