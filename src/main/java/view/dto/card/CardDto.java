package view.dto.card;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardPhrase;

public record CardDto(String cardNumber, String cardShape) {
    public CardDto(final Card card) {
        this(convertToPhrase(card.number()), card.shape().value());
    }

    private static String convertToPhrase(final CardNumber cardNumber) {
        if (cardNumber.phrase() != CardPhrase.UN_DETERMINE){
            return cardNumber.phrase().getPhrase();
        }
        return String.valueOf(cardNumber.value());
    }
}
