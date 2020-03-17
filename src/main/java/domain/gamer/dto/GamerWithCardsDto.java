package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Gamer;

import java.util.List;

public class GamerWithCardsDto {
    private String name;
    private List<Card> cards;

    private GamerWithCardsDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerWithCardsDto of(Gamer gamer) {
        List<Card> cards = gamer.getCards();
        return new GamerWithCardsDto(gamer.getName(), cards);
    }

    public static GamerWithCardsDto ofWithFirstCard(Gamer gamer) {
        List<Card> cards = gamer.getFirstCard();
        return new GamerWithCardsDto(gamer.getName(), cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
