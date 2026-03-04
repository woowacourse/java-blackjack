package domain;

public class Card {

    private final CardRank rank;
    private final CardMark mark;

    public Card(CardRank rank, CardMark mark) {
        this.rank = rank;
        this.mark = mark;
    }

    public int score() {
        return rank.score();
    }

}
