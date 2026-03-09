package blackjack.model;

public record Card(
        Rank rank,
        Suit suit
) {

    public int getScore() {
        return rank.getScore();
    }
}
