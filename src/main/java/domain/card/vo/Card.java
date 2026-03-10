package domain.card.vo;

public record Card(Rank rank, Suit suit) {
    public boolean isAce() {
        return Rank.isAce(rank);
    }

    public Integer getScore() {
        return rank.getScore();
    }
}
