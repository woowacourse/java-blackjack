package blackJack.dto;

import blackJack.domain.Card.Card;

public class CardDto {
    private String shape;
    private String number;

    private CardDto(String shape, String number) {
        this.shape = shape;
        this.number = number;
    }

    public String getCardInfo() {
        return number + shape;
    }

    public static CardDto from(Card card) {
        return new CardDto(card.getShape().getShapeName(), card.getNumber().getDenomination());
    }
}
