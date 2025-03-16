package blackjack.dto;

import blackjack.domain.card.Card;

public record CardDto(
        String type,
        String number
) {

    public static CardDto from(Card card) {
        return new CardDto(
                card.type().getDisplayName(),
                card.number().getDisplayName());
    }

    @Override
    public String toString() {
        return number + type;
    }
}
