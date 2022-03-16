package blackjack.domain;

import java.util.Objects;

public class Card {
    private final BlackjackCardType type;

    private Card(BlackjackCardType type) {
        this.type = type;
    }

    public static Card generateCard(BlackjackCardType type) {
        return new Card(type);
    }

    public BlackjackCardType type() {
        return type;
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
        return Objects.equals(type, card.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
