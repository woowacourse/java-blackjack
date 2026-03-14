package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TrumpCard {
    public static final List<TrumpCard> CARDS = Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Rank.values())
                    .map(rank -> TrumpCard.of(suit, rank)))
            .toList();

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
            Objects.requireNonNull(suit, "카드의 수트와 랭크는 null일 수 없습니다.");
            Objects.requireNonNull(rank, "카드의 수트와 랭크는 null일 수 없습니다.");
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public int getScore() {
        return rank.getScore();
    }

    public String name() {
        return rank.getSymbol() + suit.getKoreanName();
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
