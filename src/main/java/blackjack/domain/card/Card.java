package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private Type type;
    private Score score;

    public Card(final Type type, final Score score) {
        this.type = type;
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return type == card.type && score == card.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, score);
    }
}
