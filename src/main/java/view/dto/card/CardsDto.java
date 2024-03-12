package view.dto.card;

import domain.card.Cards;

import java.util.List;

public record CardsDto(List<CardDto> cards, int score) {

    public static CardsDto from(final Cards cards, final int score) {
        List<CardDto> cardDtos = cards.toList()
                                      .stream()
                                      .map(CardDto::from)
                                      .toList();
        return new CardsDto(cardDtos, score);
    }
}
