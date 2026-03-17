package dto;

import java.util.List;

public class PlayerInfoDto {
    private final String name;
    private final List<String> cards;

    public PlayerInfoDto(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
