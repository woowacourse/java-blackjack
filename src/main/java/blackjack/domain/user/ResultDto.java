package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class ResultDto {
    private String name;
    private List<Card> cards;
    private int score;

    public ResultDto(String name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
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
