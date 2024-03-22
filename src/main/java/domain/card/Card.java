package domain.card;

public record Card(Suit suit, Rank rank) {
    public int score() {
        return rank.getScore();
    }
}
