package domain.card;

public record Card(Rank rank, Symbol symbol) {

    public Score score() {
        return rank.getScore();
    }
}
