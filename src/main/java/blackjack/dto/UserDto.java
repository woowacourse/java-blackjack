package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.user.User;
import java.util.List;

public class UserDto {

    private final String userName;
    private final List<String> cardNames;

    public UserDto(String userName, List<String> cardNames) {
        this.userName = userName;
        this.cardNames = cardNames;
    }

    public static UserDto fromInit(User user) {
        return toDto(user, user.showInitCards());
    }

    public static UserDto fromEvery(User user) {
        return toDto(user, user.showCards());
    }

    private static UserDto toDto(User user, List<Card> cards) {
        return new UserDto(user.getName(), toNames(cards));
    }

    private static List<String> toNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getSymbol() + card.getSuitName())
                .collect(toList());
    }

    public String getUserName() {
        return userName;
    }

    public String getCardNames() {
        return String.join(", ", cardNames);
    }
}
