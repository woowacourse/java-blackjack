package blackjack.dto;

import blackjack.domain.card.Card;

public record CardDto(String cardNumber, String cardShape) {

    public static CardDto fromCard(Card card) {
        String name = card.number().getName();
        String shape = card.shape().getName();

        return new CardDto(name, shape);
    }
}
