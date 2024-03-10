package blackjack.dto;

import blackjack.domain.card.Hands;
import java.util.List;

public record HandsScoreDto(List<CardDto> cards, int score) {
    public static HandsScoreDto from(final Hands hands) {
        return new HandsScoreDto(convertToCardDTO(hands), hands.calculateScore().getValue());
    }

    private static List<CardDto> convertToCardDTO(final Hands hands) {
        return hands.getCards().stream()
                .map(CardDto::from)
                .toList();
    }
}
