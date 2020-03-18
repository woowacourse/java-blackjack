package view.dto;

import java.util.List;

import domain.card.Card;

public class UserDto {

    private String name;
    private List<Card> cards;

    public static UserDto of(String name, List<Card> cards) {
        return new UserDto(name, cards);
    }

    private UserDto(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
