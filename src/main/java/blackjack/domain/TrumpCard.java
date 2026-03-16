package blackjack.domain;

import java.util.Objects;

public class TrumpCard {
    private final Suit suit;
    private final Rank rank;

    private TrumpCard(Suit suit, Rank rank) {
        validate(suit, rank);
        this.suit = suit;
        this.rank = rank;
    }

    public static TrumpCard of(Suit suit, Rank rank) {
        return new TrumpCard(suit, rank);
    }

    private void validate(Suit suit, Rank rank) {
        validateSuitOrRankIsNull(suit, rank);
    }

    private void validateSuitOrRankIsNull(Suit suit, Rank rank) {
        if (suit == null || rank == null) {
            throw new IllegalArgumentException("Rank와 Suit 중 하나라도 null이 올 수 없습니다.");
        }
    }

    public String suitKoreanName() {
        return suit.getKoreanName();
    }

    public int rankScore() {
        return rank.getScore();
    }

    public String rankName() {
        return rank.getRankName();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrumpCard trumpCard = (TrumpCard) o;
        return suit == trumpCard.suit && rank == trumpCard.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
