package card;

import java.util.Objects;

public class Card {
    private final CardNumber cardNumber;
    private final Pattern pattern;

    public Card(CardNumber cardNumber, Pattern pattern) {
        this.cardNumber = cardNumber;
        this.pattern = pattern;
    }

    public String getName() {
        return cardNumber.getLabel() + pattern.getValue();
    }

    public int getScore() {
        return cardNumber.getValue();
    }

    public boolean isAce() {
        return cardNumber.getLabel().equals(CardNumber.ACE.getLabel());
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
        return cardNumber == card.cardNumber && pattern == card.pattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, pattern);
    }
}
