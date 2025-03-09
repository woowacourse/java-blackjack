package dto;

import model.card.Card;

public record CardDto(String number, String shape) {
    public static CardDto from(final Card card) {
        return new CardDto(
                card.getNumber().getName(),
                card.getShape().getName()
        );
    }
}
