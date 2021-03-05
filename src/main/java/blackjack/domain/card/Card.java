package blackjack.domain.card;

import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Value;

import java.util.Objects;

import static blackjack.domain.card.painting.Value.ACE;
import static blackjack.domain.card.painting.Value.ACE_OF_ONE;

public class Card {
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public boolean isAce() {
        return value == ACE;
    }

    public Card toAceOfOne() {
        return new Card(this.suit, ACE_OF_ONE);
    }

    public boolean isSameSuit(Suit suit) {
        return this.suit == suit;
    }

    public String getSuitLetter() {
        return suit.getLetter();
    }

    public boolean isSameValue(Value value) {
        return this.value == value;
    }

    public String getValueLetter() {
        return value.getLetter();
    }

    public int getScore() {
        return value.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, value);
    }
}
