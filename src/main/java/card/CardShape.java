package card;

public enum CardShape {

    SPADE,
    DIAMOND,
    HEART,
    CLOVER,
    ;

    public boolean isSame(CardShape cardShape) {
        return this.equals(cardShape);
    }
}
