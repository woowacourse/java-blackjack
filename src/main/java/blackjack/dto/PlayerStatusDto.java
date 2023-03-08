package blackjack.dto;

import java.util.List;

public class PlayerStatusDto {

    private final String name;
    private final List<String> cards;
    private final int point;

    public PlayerStatusDto(String name, List<String> cards, int point) {
        this.name = name;
        this.cards = cards;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getPoint() {
        return point;
    }
}
