package dto;

import domain.Player;

import java.util.List;

public class PlayerDto {
    private final String name;
    private final List<String> cards;

    private PlayerDto(final String name, final List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerDto from(final Player player) {
        return new PlayerDto(player.getName(), player.getCards());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
