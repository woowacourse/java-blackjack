package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.result.Score;
import java.util.List;

public record HandsScoreDTO(List<CardDTO> cards, int score) {
    public static HandsScoreDTO of(final Hands hands, final Score score) {
        return new HandsScoreDTO(convertToCardDTO(hands), score.getValue());
    }

    private static List<CardDTO> convertToCardDTO(final Hands hands) {
        return hands.getCards().stream()
                .map(CardDTO::from)
                .toList();
    }
}
