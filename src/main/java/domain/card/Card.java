package domain.card;

import java.util.Objects;

public class Card {

    private final CardDenomination denomination;
    private final CardEmblem emblem;

    private Card(CardDenomination denomination, CardEmblem emblem) {
        this.denomination = denomination;
        this.emblem = emblem;
    }

    public static Card of(CardDenomination denomination, CardEmblem emblem) {
        return new Card(denomination, emblem);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Card card = (Card) object;
        return denomination == card.denomination && emblem == card.emblem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, emblem);
    }

    public String toDisplay() {
        return denomination.getDisplayName() + emblem.displayName();
    }

    public int getScore() {
        return denomination.getScore();
    }
}
