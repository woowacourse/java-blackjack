package blackjack.domain.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Shape;

import java.util.Arrays;

public class CardDto {
    private final String shape;
    private final String value;

    public CardDto(String shape, String value) {
        this.shape = shape;
        this.value = value;
    }

    public String getShape() {
        return shape;
    }

    public String getValue() {
        return value;
    }

}
