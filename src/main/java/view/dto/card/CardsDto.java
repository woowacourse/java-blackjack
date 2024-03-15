package view.dto.card;

import java.util.List;

public record CardsDto(List<CardDto> cardsDto, int score) {
}
