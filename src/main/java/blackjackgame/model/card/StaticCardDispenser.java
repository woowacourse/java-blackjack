package blackjackgame.model.card;

public class StaticCardDispenser implements CardDispenser {

    private final CardNumber cardNumber;
    private final CardShape cardShape;

    public StaticCardDispenser(CardNumber cardNumber, CardShape cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public CardNumber generateCardNumber() {
        return cardNumber;
    }

    public CardShape generateCardShape() {
        return cardShape;
    }
}
