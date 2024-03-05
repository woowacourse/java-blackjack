package domain.card;

public class PlayingCard {
    private final PlayingCardShape playingCardShape;
    private final PlayingCardValue playingCardValue;

    public PlayingCard(final PlayingCardShape playingCardShape, final PlayingCardValue playingCardValue) {
        this.playingCardShape = playingCardShape;
        this.playingCardValue = playingCardValue;
    }

    public int addValue(int inputValue) {
        return inputValue + playingCardValue.getValue();
    }
}
