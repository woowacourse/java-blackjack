package blackjack.model.card;

public class CardProperties {

    private final CardPattern cardPattern;
    private final CardNumber cardNumber;

    public CardProperties(CardPattern cardPattern, CardNumber cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public CardPattern getCardPattern() {
        return cardPattern;
    }
}
