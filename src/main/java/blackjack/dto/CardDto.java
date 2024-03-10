package blackjack.dto;

import blackjack.domain.card.Card;

public record CardDto(String number, String shape) {

    public static CardDto from(final Card dealerCard) {
        return new CardDto(dealerCard.getNumberName(), dealerCard.getShapeName());
    }
}
