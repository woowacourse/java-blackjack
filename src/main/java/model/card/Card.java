package model.card;

public class Card {

    private final SuitType suit;
    private final RankType rank;

    public Card(SuitType suit, RankType rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int adjustRank() {
        return rank.adjustRankScore();
    }

    public int getRankScore() {
        return rank.getRankScore();
    }

    public SuitType getSuit() {
        return suit;
    }

    public RankType getRank() {
        return rank;
    }
}
