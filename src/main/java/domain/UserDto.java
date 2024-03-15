package domain;

import domain.card.Card;
import domain.user.User;

import java.util.List;

public class UserDto {
    public final String name;
    public final List<String> cards;
    public final int sumOfCards;

    private UserDto(String name, List<String> cards, int sumOfCards) {
        this.name = name;
        this.cards = cards;
        this.sumOfCards = sumOfCards;
    }

    public static UserDto from(User user) {
        List<String> cards = user.getUserDeck().getCards().stream()
                .map(Card::getName)
                .toList();
        return new UserDto(user.getName(), cards, user.getUserDeck().sumCard());
    }

    public String getFirstCard() {
        return cards.get(0);
    }
}
