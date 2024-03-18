package domain.card;

public record Card(Rank rank, Suit suit) {
    public int getRankValue() {
        return rank.getValue();
    }
}
