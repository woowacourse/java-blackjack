package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.List;

public class CurrentCardsDto {

    private final String name;
    private final List<Card> cards;

    public CurrentCardsDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
