package dto;

import domain.player.Player;

import java.util.List;

public final class PlayerDto {

    private final String name;
    private final List<String> cards;
    private final int score;

    private PlayerDto(String name, List<String> card, int score) {
        this.name = name;
        this.cards = card;
        this.score = score;
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getCardNames(), player.getScore());
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
