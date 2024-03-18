package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final Pattern pattern;
    private final Rank rank;

    public Card(Pattern pattern, Rank rank) {
        this.pattern = pattern;
        this.rank = rank;
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public int getCardScore() {
        return rank.getNumber();
    }

    public Rank getCardNumber() {
        return rank;
    }

    public Pattern getCardPattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return pattern == card.pattern && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, rank);
    }
}
