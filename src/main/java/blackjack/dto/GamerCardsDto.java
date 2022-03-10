package blackjack.dto;

import java.util.List;

public class GamerCardsDto {
    private final String name;
    private final List<CardDto> cards;

    public GamerCardsDto(String name, List<CardDto> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return cards;
    }
}
