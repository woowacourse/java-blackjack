package blackjack.domain.player;

import blackjack.domain.card.Cards;

import java.util.Objects;

public class Challenger extends Player {
    private final String name;
    private boolean isBust;

    public Challenger(final Cards cards, final String name) {
        super(cards);
        this.name = name;
        this.isBust = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenger that = (Challenger) o;
        return isBust == that.isBust && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isBust);
    }
}
