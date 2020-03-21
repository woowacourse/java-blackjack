package domain.gamer.dto;

import domain.card.Card;

import java.util.List;

public class GamerCardsDto extends GamerBaseDto {
    private List<Card> cards;

    public GamerCardsDto(String name, List<Card> cards) {
        super(name);
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
