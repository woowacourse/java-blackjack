package domain;

public record Card(CardRank rank, CardMark mark) {

    boolean isAce() {
        return rank == CardRank.ACE;
    }

    int score() {
        return rank.score();
    }
}
