package dto;

import domain.card.Card;
import formatter.CardFormatter;

public record CardDto(String cardName) {

    public static CardDto from(Card card) {
        return new CardDto(CardFormatter.format(card));
    }
}
