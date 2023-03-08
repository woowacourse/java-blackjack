package dto;

import domain.Card;

public class CardStatusDto {

    private final String letter;
    private final String shape;

    private CardStatusDto(String letter, String shape) {
        this.letter = letter;
        this.shape = shape;
    }

    public static CardStatusDto from(Card card) {
        return new CardStatusDto(card.getLetter().getExpression(), card.getShape().getName());
    }

    public String getLetter() {
        return letter;
    }

    public String getShape() {
        return shape;
    }

}
