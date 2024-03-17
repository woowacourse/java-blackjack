package view.dto;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.player.Name;

public class UserResultDto {

    private final String name;
    private final List<String> cards;
    private final int score;

    public UserResultDto(Name name, Cards cards) {
        this.name = name.getValue();
        this.cards = cards.getCards().stream()
                .map(this::cardToString)
                .toList();
        this.score = cards.calculateScore();
    }

    private String cardToString(Card card) {
        return card.getNumber().getName() + card.getShape().getName();
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
