package domain;

public record Card(CardRank rank, CardMark mark) {

    String info() {
        return rank.label() + mark.description();
    }

    int score() {
        return rank.score();
    }
}
