package BlackJack.dto;

import BlackJack.domain.Card.Card;

public class CardDto {
    private final String shape;
    private final String number;

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
