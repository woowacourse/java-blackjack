package blackjack.dto;

import blackjack.domain.card.Card;

public class CardDto {

    private final String cardNumber;
    private final String cardShape;

    private CardDto(String cardNumber, String cardShape) {
        this.cardNumber = cardNumber;
        this.cardShape = cardShape;
    }

    public static CardDto fromCard(Card card) {
        String name = card.number().getName();
        String shape = card.shape().getName();

        return new CardDto(name, shape);
    }

    public String cardNumber() {
        return cardNumber;
    }

    public String cardShape() {
        return cardShape;
    }
}
