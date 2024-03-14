package domain;

public record PlayingCard(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {
    public int getValue(final int primitiveSum) {
        return playingCardValue.getValue(primitiveSum);
    }
}
