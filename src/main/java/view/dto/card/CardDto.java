package view.dto.card;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.Arrays;

public record CardDto(String cardNumber, String cardShape) {
    public static CardDto from(final Card card) {
        return new CardDto(convertToPhrase(card.number()), card.shape().value());
    }

    private static String convertToPhrase(final CardNumber cardNumber) {
        return Arrays.stream(CardPhrase.values())
                     .filter(cardPhrase -> cardPhrase.name() == cardNumber.name())
                     .map(CardPhrase::getPhrase)
                     .findFirst()
                     .orElse(String.valueOf(cardNumber.value()));
    }
}
