package domain.gamer.dto;

import domain.card.Card;
import domain.gamer.Gamer;

import java.util.List;

public class GamerWithScoreDto {
    private String name;
    private List<Card> cards;
    private int score;

    private GamerWithScoreDto(String name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static GamerWithScoreDto of(Gamer gamer) {
        List<Card> cards = gamer.getCards();
        return new GamerWithScoreDto(gamer.getName(), cards, gamer.calculateScore().getScore());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}

