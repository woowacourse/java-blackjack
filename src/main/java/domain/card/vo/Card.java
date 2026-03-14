package domain.card.vo;

import domain.score.Score;

public record Card(Rank rank, Suit suit) {
    public boolean isAce() {
        return Rank.isAce(rank);
    }

    public Score getScore() {
        return rank.getScore();
    }
}
