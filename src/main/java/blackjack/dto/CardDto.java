package blackjack.dto;

import blackjack.domain.card.Card;

public class CardDto {

    private final String cardPattern;
    private final String cardNumber;

    private CardDto(String cardPattern, String cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }

    public static CardDto from(Card card) {
        return new CardDto(card.getPatternName(), card.getNumberName());
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPattern() {
        return cardPattern;
    }
}
