package view.dto.card;

import domain.card.Cards;

import java.util.List;

public record CardsDto(List<CardDto> cards, int score) {

    public CardsDto(final Cards cards, final int score) {
        this(convertToCardDtos(cards), score);
    }

    private static List<CardDto> convertToCardDtos(final Cards cards) {
        return cards.toList()
                    .stream()
                    .map(CardDto::new)
                    .toList();
    }
}
