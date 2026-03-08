package blackjack.dto.response;

import blackjack.domain.TrumpCard;

public record CardDto(
        String display
) {
    public static CardDto from(TrumpCard card) {
        return new CardDto(card.symbol() + card.koreanName());
    }
}
