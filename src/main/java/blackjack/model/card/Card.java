package blackjack.model.card;

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

    public Rank getCardNumber() {
        return rank;
    }

    public Pattern getCardPattern() {
        return pattern;
    }

    public int getCardScore() {
        return rank.getNumber();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Card that = (Card) object;
        return pattern == that.pattern && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, rank);
    }
}
