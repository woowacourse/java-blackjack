package blackjack.domain.card;

public enum CardSpec {
    ACE(Card.of(CardNumber.ACE, Symbol.CLOVER)),
    TWO(Card.of(CardNumber.TWO, Symbol.CLOVER)),
    THREE(Card.of(CardNumber.THREE, Symbol.CLOVER)),
    FOUR(Card.of(CardNumber.FOUR, Symbol.CLOVER)),
    FIVE(Card.of(CardNumber.FIVE, Symbol.CLOVER)),
    SIX(Card.of(CardNumber.SIX, Symbol.CLOVER)),
    SEVEN(Card.of(CardNumber.SEVEN, Symbol.CLOVER)),
    EIGHT(Card.of(CardNumber.EIGHT, Symbol.CLOVER)),
    NINE(Card.of(CardNumber.NINE, Symbol.CLOVER)),
    TEN(Card.of(CardNumber.TEN, Symbol.CLOVER)),
    JACK(Card.of(CardNumber.JACK, Symbol.CLOVER)),
    QUEEN(Card.of(CardNumber.QUEEN, Symbol.CLOVER)),
    KING(Card.of(CardNumber.KING, Symbol.CLOVER));

    private final Card card;

    CardSpec(Card card) {
        this.card = card;
    }

    public Card card() {
        return card;
    }
}
