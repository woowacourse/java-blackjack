package common;

import domain.card.Card;
import domain.gamer.Gamer;

import java.util.List;

public class GamerScoreDto {
    private String name;
    private List<Card> cards;

    private GamerScoreDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerScoreDto of(Gamer gamer) {
        List<Card> cards = gamer.getPlayingCards().getCards();
        return new GamerScoreDto(gamer.getName(), cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
