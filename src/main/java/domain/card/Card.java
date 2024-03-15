package domain.card;

public record Card(CardSuit suit, CardRank rank) {
    public int score() {
        return rank.getScore();
    }
}
