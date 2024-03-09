package domain;

public record PlayingCard(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {

    public int getValue(final int inputValue) {
        return playingCardValue.getValue(inputValue);
    }
}
