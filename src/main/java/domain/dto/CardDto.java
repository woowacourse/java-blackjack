package domain.dto;

import domain.Card;

public record CardDto(String rankName, String shapeName) {
    public static CardDto from(Card card) {
        return new CardDto(card.cardShape().getName(), card.cardRank().getName());
    }
}
