package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;

public class CardDto {

    private final String initial;
    private final String pattern;

    private CardDto(final String initial, final String pattern) {
        this.initial = initial;
        this.pattern = pattern;
    }

    public static CardDto toDto(final Card card) {
        final CardNumber cardNumber = card.getCardNumber();
        final CardPattern cardPattern = card.getCardPattern();
        return new CardDto(cardNumber.getInitial(), cardPattern.getName());
    }

    public String getCardName() {
        return initial + pattern;
    }

}
