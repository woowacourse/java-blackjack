package dto;

import domain.Card;

public class CardStatusDto {

    private final String letterExpression;

    private final String shapeName;

    public CardStatusDto(Card card) {
        this.letterExpression = card.getLetterExpression();
        this.shapeName = card.getShapeName();
    }

    public String getLetterExpression() {
        return letterExpression;
    }

    public String getShapeName() {
        return shapeName;
    }

}
