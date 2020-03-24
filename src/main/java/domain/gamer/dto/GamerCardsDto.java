package domain.gamer.dto;

import domain.card.Card;

import java.util.List;

public class GamerCardsDto {
    private String name;
    private List<Card> cards;

    public GamerCardsDto(String name, List<Card> cards) {
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
