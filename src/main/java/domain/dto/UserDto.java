package domain.dto;

import domain.card.Card;
import domain.card.Score;
import domain.user.Name;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    private final String name;
    private final int score;
    private final List<String> cards;

    public UserDto(Name name, Score score, List<Card> cards) {
        this.name = name.getName();
        this.score = score.getScore();
        this.cards = cards.stream()
                .map(Card::getSymbol)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<String> getCards() {
        return cards;
    }
}
