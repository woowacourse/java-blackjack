package domain.playingcard;

public record PlayingCard(PlayingCardShape playingCardShape, PlayingCardValue playingCardValue) {

    public boolean isAce() {
        return playingCardValue.isAce();
    }
}
