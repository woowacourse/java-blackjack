package domain.card;

import java.util.Objects;

public class Card {
    private final Denomination denomination;
    private final Emblem emblem;

    public Card(Denomination denomination, Emblem emblem) {
        this.denomination = denomination;
        this.emblem = emblem;
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public Score getScore() {
        return denomination.getScore();
    }

    public Denomination getDenomination() {
        return this.denomination;
    }

    public Emblem getEmblem() {
        return this.emblem;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Card other) {
            return this.denomination == other.denomination && this.emblem == other.emblem;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, emblem);
    }
}
