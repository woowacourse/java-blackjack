package domain.dto;

import domain.card.Card;

public record CardDto(String rankName, String shapeName) {
    public static CardDto from(Card card) {
        return new CardDto(card.cardShape().getName(), card.cardRank().getName());
    }
}
