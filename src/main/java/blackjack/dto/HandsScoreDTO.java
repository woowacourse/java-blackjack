package blackjack.dto;

import blackjack.domain.Hands;
import blackjack.domain.Score;
import java.util.List;

public record HandsScoreDTO(List<CardDTO> cards, int score) {
    public static HandsScoreDTO of(final Hands hands, final Score score) {
        return new HandsScoreDTO(converToCardDTO(hands), score.getValue());
    }

    private static List<CardDTO> converToCardDTO(final Hands hands) {
        return hands.getCards().stream()
                .map(CardDTO::from)
                .toList();
    }
}
