package dto;

import domain.model.Card;

public record CardDto(
        String shape,
        String rank
) {
    public static CardDto of(Card card) {
        return new CardDto(card.getCardShape().getName(), card.getCardRank().getName());
    }

    @Override
    public String toString() {
        return rank + shape;
    }
}
