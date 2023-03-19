package blackjack.dto;

import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class CardDto {

    private final Suit suit;
    private final Denomination denomination;

    public CardDto(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
