package blackjack.domain.card;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Card {

    private final CardPattern cardPattern;
    private final CardNumber cardNumber;

    public Card(final CardPattern cardPattern, final CardNumber cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }

    public Set<Integer> addNumbers(final int otherNumber) {
        final List<Integer> numbers = cardNumber.getNumbers();
        return numbers.stream()
                .map(number -> otherNumber + number)
                .collect(Collectors.toSet());
    }

    public CardPattern getCardPattern() {
        return cardPattern;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
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
        return "Card{" +
                cardNumber.getInitial() +
                cardPattern.getPattern() +
                '}';
    }

}
