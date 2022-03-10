package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.user.User;
import java.util.List;

public class UserDto {

    private final String userName;
    private final List<String> cardNames;

    private UserDto(String userName, List<String> cardNames) {
        this.userName = userName;
        this.cardNames = cardNames;
    }

    public static UserDto from(User user) {
        String name = user.getName();
        List<Card> cards = user.showCards();

        List<String> cardNames = cards.stream()
                .map(card -> card.getSymbol() + card.getSuitName())
                .collect(toList());

        return new UserDto(name, cardNames);
    }

    public String getUserName() {
        return userName;
    }

    public String getCardNames() {
        return String.join(", ", cardNames);
    }
}
