package domain.card;

public class Card {

    private final Rank rank;
    private final Symbol symbol;

    public Card(Rank rank, Symbol symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public int score() {
        return rank.getScore();
    }
}
