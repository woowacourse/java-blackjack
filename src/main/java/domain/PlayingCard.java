package domain;

public record PlayingCard(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {
    public int getValue() {
        return playingCardValue.getValue();
    }

    public boolean isAce() {
        return playingCardValue.isAce();
    }
}
