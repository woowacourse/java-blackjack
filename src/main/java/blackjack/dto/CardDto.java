package blackjack.dto;

import blackjack.trumpcard.Card;

public class CardDto {
    private String card;

    public CardDto(Card card) {
        this.card = card.toString();
    }

    public String getCard() {
        return card;
    }
}
