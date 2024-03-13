package dto;

import domain.card.Card;

public record CardResponse(String rank, String suit) {

    public static CardResponse of(final Card card) {
        return new CardResponse(card.getRank().name(), card.getSymbol().name());
    }
}
