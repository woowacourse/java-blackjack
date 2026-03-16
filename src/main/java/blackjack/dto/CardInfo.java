package blackjack.dto;

import blackjack.domain.TrumpCard;

public record CardInfo(
        String display
) {
    public static CardInfo from(TrumpCard card) {
        return new CardInfo(card.rankName() + card.suitKoreanName());
    }
}
