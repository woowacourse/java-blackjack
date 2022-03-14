package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class CardDto {

    private final Suit suit;
    private final Denomination denomination;

    public CardDto(Card card) {
        this.suit = card.getSuit();
        this.denomination = card.getDenomination();
    }

    public String getSymbol() {
        return denomination.getSymbol();
    }

    public String getSuitName() {
        return suit.getName();
    }
}
