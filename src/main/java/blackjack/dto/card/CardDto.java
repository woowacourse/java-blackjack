package blackjack.dto.card;

import blackjack.domain.card.Card;

public record CardDto(
    CardTypeDto type,
    CardNumberDto number
) {

    public static CardDto from(Card card) {
        return new CardDto(
            CardTypeDto.from(card.type()),
            CardNumberDto.from(card.number()));
    }
}
