package blackjack.domain.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

public record CardDto(CardNumber cardNumber, CardShape cardShape) {

    public static CardDto fromCard(Card card) {
        return new CardDto(card.getCardNumber(), card.getCardShape());
    }
}
