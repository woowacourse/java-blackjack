package blackjack.dto;

import blackjack.domain.Card;

public class CardDto {

    private final String denomination;
    private final String suit;

    private CardDto(String denomination, String suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public static CardDto from(Card card) {
        return new CardDto(card.getDenomination().getName(), card.getSuit().getName());
    }

    public String getDenomination() {
        return denomination;
    }

    public String getSuit() {
        return suit;
    }
}
