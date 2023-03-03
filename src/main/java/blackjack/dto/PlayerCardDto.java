package blackjack.dto;

import blackjack.domain.Card;

import java.util.List;

public class PlayerCardDto {
    private final String name;
    private final List<Card> cards;

    public PlayerCardDto(final String name, final List<Card> cards) {
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
