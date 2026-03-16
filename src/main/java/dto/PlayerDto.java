package dto;

import domain.card.Card;
import domain.participant.Player;

import java.util.List;

public class PlayerDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public PlayerDto(Player player) {
        this.name = player.getName();
        this.cards = List.copyOf(player.getCards());
        this.score = player.getScore();
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
