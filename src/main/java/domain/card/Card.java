package domain.card;

public record Card(Rank rank, Suit suit) {

    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }

    public int score() {
        return rank.value();
    }
}
