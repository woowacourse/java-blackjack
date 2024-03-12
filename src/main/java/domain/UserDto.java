package domain;

import domain.card.Card;
import domain.user.Name;
import domain.user.User;

import java.util.List;

public class UserDto {
    public final Name name;
    public final List<Card> cards;
    public final int sumOfCards;

    private UserDto(Name name, List<Card> cards, int sumOfCards) {
        this.name = name;
        this.cards = cards;
        this.sumOfCards = sumOfCards;
    }

    public static UserDto from(User user) {
        return new UserDto(user.getName(), user.getUserDeck().getCards(), user.getUserDeck().sumCard());
    }
}
