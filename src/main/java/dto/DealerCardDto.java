package dto;

import domain.card.Card;
import java.util.List;

public record DealerCardDto(List<CardDto> cards) {

    public static DealerCardDto from(List<Card> cards) {
        return new DealerCardDto(cards.stream()
                .map(CardDto::from)
                .toList());
    }
}
