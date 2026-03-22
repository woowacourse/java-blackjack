package ui.dto;

import domain.card.Card;
import java.util.List;

public record CardDto(
        RankDto rank,
        SuitDto suit
) {

    public static CardDto toDto(Card card) {
        return new CardDto(RankDto.toDto(card.getRank()), SuitDto.toDto(card.getSuit()));
    }

    public static List<CardDto> toDtoList(List<Card> cards) {
        return cards.stream()
                .map(CardDto::toDto)
                .toList();
    }
}
