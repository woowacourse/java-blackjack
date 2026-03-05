package domain.card;

import java.util.Objects;

public class Card {

    private final CardEmblem emblem;
    private final CardDenomination denomination;

    private Card(CardEmblem emblem, CardDenomination denomination) {
        this.emblem = emblem;
        this.denomination = denomination;
    }

    public static Card of(CardEmblem emblem, CardDenomination denomination) {
        return new Card(emblem, denomination);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Card card = (Card) object;
        return emblem == card.emblem && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emblem, denomination);
    }

}
