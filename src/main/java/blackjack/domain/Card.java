package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private final Pattern pattern;
    private final Denomination denomination;

    public static List<Card> create(Pattern selectedPattern) {
        return Arrays.stream(Denomination.values())
            .map((number) -> new Card(selectedPattern, number))
            .collect(Collectors.toList());
    }

    public Card(Pattern pattern, Denomination denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int getValue() {
        return denomination.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return pattern == card.pattern && Objects.equals(denomination, card.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, denomination);
    }

    @Override
    public String toString() {
        return denomination.getSymbol() + pattern.getName();
    }
}
