package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.user.User;
import java.util.List;

public class UserDto {

    private final String userName;
    private final List<CardDto> cards;
    private final List<CardDto> initCards;

    private UserDto(String userName, List<CardDto> cards, List<CardDto> initCards) {
        this.userName = userName;
        this.cards = cards;
        this.initCards = initCards;
    }

    public static UserDto from(User user) {
        String name = user.getName();

        List<CardDto> cards = getCards(user);

        List<CardDto> initCards = getInitCards(user);

        return new UserDto(name, cards, initCards);
    }

    private static List<CardDto> getInitCards(User user) {
        return user.showInitCards().stream()
                .map(card -> new CardDto(card))
                .collect(toList());
    }

    private static List<CardDto> getCards(User user) {
        List<Card> cards = user.showCards();

        return cards.stream()
                .map(card -> new CardDto(card))
                .collect(toList());
    }

    public String getUserName() {
        return userName;
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public List<CardDto> getInitCards() {
        return initCards;
    }
}
