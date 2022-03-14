package blackjack.dto;

import blackjack.domain.Card;

public class CardDto {

    private final String value;

    public CardDto(String value) {
        this.value = value;
    }

    public static CardDto from(Card card) {
        String cardValue = card.getDenomination().getName() + card.getSuit().getName();
        return new CardDto(cardValue);
    }

    public String getValue() {
        return value;
    }
}
