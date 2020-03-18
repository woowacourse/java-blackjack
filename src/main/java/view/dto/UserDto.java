package view.dto;

import java.util.List;

import domain.card.Card;
import domain.user.User;

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
