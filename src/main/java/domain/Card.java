package domain;

public record Card(CardRank rank, CardMark mark) {

    boolean isAce() {
        return rank == CardRank.ACE;
    }

    String info() {
        return rank.label() + mark.description();
    }

    int score() {
        return rank.score();
    }
}
