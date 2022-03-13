package domain.card;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public final class Card {
    private final Suit suit;
    private final Face face;

    public Card(final Suit suit, final Face face) {
        this.suit = suit;
        this.face = face;
    }

    public boolean isAceCard() {
        return face.isAce();
    }

    public int getScore() {
        return face.getScore();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && face == card.face;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, face);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public Face getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }
}
