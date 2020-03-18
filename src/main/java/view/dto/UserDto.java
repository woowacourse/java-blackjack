package view.dto;

import domain.card.Card;
import domain.user.User;

import java.util.List;

public class UserDto {

    private String name;
    private List<Card> cards;

    public static UserDto of(User user) {
        return new UserDto(user);
    }

    private UserDto(User user) {
        this.name = user.getName();
        this.cards = user.getCards();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
