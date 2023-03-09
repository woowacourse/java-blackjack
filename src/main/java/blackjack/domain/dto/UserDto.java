package blackjack.domain.dto;

import java.util.List;

public class UserDto {
    private final String name;
    private final List<CardDto> cards;

    public UserDto(String name, List<CardDto> cards) {
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
