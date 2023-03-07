package balckjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private final Pattern pattern;
    private final Number number;

    public static List<Card> create(Pattern selectedPattern) {
        return Arrays.stream(Number.values())
            .map((number) -> new Card(selectedPattern, number))
            .collect(Collectors.toList());
    }

    public Card(Pattern pattern, Number number) {
        this.pattern = pattern;
        this.number = number;
    }

    public boolean isAce() {
        return number == Number.ACE;
    }

    public int getValue() {
        return number.getValue();
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
        return pattern == card.pattern && Objects.equals(number, card.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }

    @Override
    public String toString() {
        return number.getSymbol() + pattern.getName();
    }
}
