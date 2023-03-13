package blackjack.dto;

import blackjack.domain.card.Card;

public class CardDto {

    private final String shape;
    private final String number;

    public CardDto(Card card) {
        this.shape = card.getShape().getView();
        this.number = card.getNumber().getView();
    }

    public String getNumber() {
        return number;
    }

    public String getShape() {
        return shape;
    }
}
