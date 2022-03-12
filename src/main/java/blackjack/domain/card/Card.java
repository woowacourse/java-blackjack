package blackjack.domain.card;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Card {

    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(final CardNumber cardNumber, final CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public String getCardName() {
        return cardNumber.getInitial() + cardPattern.getName();
    }

    public Set<Integer> addNumbers(final int otherNumber) {
        final List<Integer> numbers = cardNumber.getNumbers();
        return numbers.stream()
                .map(number -> otherNumber + number)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card other = (Card) o;
        return (cardNumber == other.cardNumber) && (cardPattern == other.cardPattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardPattern, cardNumber);
    }

    @Override
    public String toString() {
        return "Card{" + getCardName() + '}';
    }

}
