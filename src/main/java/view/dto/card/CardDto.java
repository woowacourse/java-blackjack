package view.dto.card;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardNumberPhrase;

import java.util.Arrays;

public record CardDto(String cardNumber, String cardShape) {
    public CardDto(final Card card) {
        this(convertToPhrase(card.number()), card.shape().value());
    }

    private static String convertToPhrase(final CardNumber cardNumber) {
        return Arrays.stream(CardNumberPhrase.values())
                     .filter(cardNumberPhrase -> cardNumberPhrase.name() == cardNumber.name())
                     .map(CardNumberPhrase::getPhrase)
                     .findFirst()
                     .orElse(String.valueOf(cardNumber.value()));
    }
}
