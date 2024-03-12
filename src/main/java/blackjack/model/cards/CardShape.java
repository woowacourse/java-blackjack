package blackjack.model.cards;

public enum CardShape {
    SPADE,
    DIAMOND,
    HEART,
    CLOVER;

    public static CardShape generate(int index) {
        return values()[index];
    }

}
