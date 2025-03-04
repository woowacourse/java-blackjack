public enum CardSuit {

    HEART(CardType.NUMBER),
    SPADE(CardType.NUMBER),
    DIAMOND(CardType.NUMBER),
    CLOVER(CardType.NUMBER),
    KING(CardType.FACE),
    QUEEN(CardType.FACE),
    JACK(CardType.FACE);

    private final CardType cardType;

    CardSuit(CardType cardType) {
        this.cardType = cardType;
    }

    class Card {
        private CardValue cardValue;
        private CardSuit cardSuit;
    }
}