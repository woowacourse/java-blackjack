package domain.card;

import domain.Rank;
import domain.Suit;

import java.util.Objects;

public class Card {
    public static final String FIELD_CAN_NOT_BE_NULL = "[ERROR] 멤버 변수로 Null을 넣을 수 없습니다.";
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        validate(suit, rank);
        this.suit = suit;
        this.rank = rank;
    }

    private void validate(Suit suit, Rank rank) {
        if (suit == null || rank == null) {
            throw new IllegalArgumentException(FIELD_CAN_NOT_BE_NULL);
        }
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
}
