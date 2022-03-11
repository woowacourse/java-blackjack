package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.user.User;
import java.util.List;

public class UserDto {

    private final String userName;
    private final List<String> cardsInfo;
    private final List<String> initCardsInfo;

    private UserDto(String userName, List<String> cardsInfo, List<String> initCardsInfo) {
        this.userName = userName;
        this.cardsInfo = cardsInfo;
        this.initCardsInfo = initCardsInfo;
    }

    public static UserDto from(User user) {
        String name = user.getName();

        List<Card> cards = user.showCards();

        List<String> cardsInfo = getCardsInfo(cards);

        List<String> initCardsInfo = getInitCardsInfo(user);

        return new UserDto(name, cardsInfo, initCardsInfo);
    }

    private static List<String> getInitCardsInfo(User user) {
        return user.showInitCards().stream()
                .map(card -> card.getSymbol() + card.getSuitName())
                .collect(toList());
    }

    private static List<String> getCardsInfo(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getSymbol() + card.getSuitName())
                .collect(toList());
    }

    public String getUserName() {
        return userName;
    }

    public String getCardsInfo() {
        return String.join(", ", cardsInfo);
    }

    public String getInitCardsInfo() {
        return String.join(", ", initCardsInfo);
    }
}
