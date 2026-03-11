package dto;

import domain.Card;

public record CardDto(String cardShape, String cardContentNumber) {
    public static CardDto from(Card card) {
        String cardShape = card.getCardShape().getName();
        String cardContents = card.getCardContents().getNumber();
        return new CardDto(cardShape, cardContents);
    }
}
