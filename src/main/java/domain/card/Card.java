package domain.card;

public record Card(Rank rank, Suit suit) {

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank.isAce();
    }
}
