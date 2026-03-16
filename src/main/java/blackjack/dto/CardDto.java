package blackjack.dto;

import blackjack.domain.TrumpCard;

public record CardDto(
        String display
) {
    public static CardDto from(TrumpCard card) {
        return new CardDto(card.name());
    }
}
