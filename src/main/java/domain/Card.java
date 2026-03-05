package domain;

public class Card {

    private final CardRank rank;
    private final CardMark mark;

    public Card(CardRank rank, CardMark mark) {
        this.rank = rank;
        this.mark = mark;
    }

    public String info() {
        return rank.label() + mark.description();
    }

    public int score() {
        return rank.score();
    }

    public CardRank rank() {
        return rank;
    }
}
