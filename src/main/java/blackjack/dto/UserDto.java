package blackjack.dto;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.user.User;
import java.util.List;

public class UserDto {

    private final String userName;
    private final List<String> cardNames;
    private final int score;

    public UserDto(String userName, List<String> cardNames, int score) {
        this.userName = userName;
        this.cardNames = cardNames;
        this.score = score;
    }

    public static UserDto fromInit(User user) {
        return toDto(user, user.showInitCards(), user.getScore());
    }

    public static UserDto fromEvery(User user) {
        return toDto(user, user.getHandCards(), user.getScore());
    }

    private static UserDto toDto(User user, List<Card> cards, Score score) {
        return new UserDto(user.getName(), toNames(cards), score.getScore());
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

    public int getScore() {
        return score;
    }
}
