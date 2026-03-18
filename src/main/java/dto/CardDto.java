package dto;

import domain.card.Card;

public record CardDto(String cardName) {

    public static CardDto from(Card card) {
        return new CardDto(card.rankString() + card.suitString());
    }
}
