package domain;

import java.util.Objects;

import constant.Suit;

public record TrumpCard(
    Rank rank,
    Suit suit
) {
    public boolean isMatchRank(final Rank rank) {
        return Objects.equals(rank, rank);
    }

    public int sumScore(final int score) {
        return rank.sum(score);
    }
}
