package domain;

public record PlayingCard(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {

    public int addValue(final int inputValue) {
        return playingCardValue.getValue(inputValue);
    }
}
