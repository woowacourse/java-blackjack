package blackjack.dto;

import blackjack.domain.card.Card;

public record CardDto(
    String type,
    String number
) {

    public static CardDto from(Card card) {
        return new CardDto(
            card.getCardType().getDisplayName(),
            card.getCardNumber().getDisplayName());
    }
}
