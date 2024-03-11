package domain.card;

public record Card(Rank rank, Symbol symbol) {

    public int score() {
        return rank.getScore();
    }
}
