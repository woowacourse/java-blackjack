package dto;

import domain.card.Card;
import java.util.List;

public record DealerCardWithScoreDto(List<CardDto> cards, int score) {

    public static DealerCardWithScoreDto from(List<Card> cards, int score) {
        return new DealerCardWithScoreDto(cards.stream()
                .map(CardDto::from)
                .toList(), score);
    }
}
