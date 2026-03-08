package domain.card;

import java.util.Objects;

public class Card {

    private final CardPattern pattern;
    private final CardNumber number;

    public Card(String number, String pattern) {
        this.number = CardNumber.matchCardNumber(number);
        this.pattern = CardPattern.matchCardPattern(pattern);
    }

    public int number() {
        return number.getValue();
    }

    public boolean isAce() {
        return number.isAce();
    }

    public String cardName() {
        return number.getCourt() + pattern.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card card)) {
            return false;
        }
        return pattern == card.pattern && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, number);
    }
}
