package blackjack.model.dto;

import java.util.List;

public class PlayerDTO {

    private final String name;
    private final int score;
    private final List<CardDTO> cards;

    public PlayerDTO(String name, int score, List<CardDTO> cards) {
        this.name = name;
        this.score = score;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<CardDTO> getCards() {
        return cards;
    }
}
