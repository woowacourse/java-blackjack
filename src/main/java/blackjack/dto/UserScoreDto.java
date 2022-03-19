package blackjack.dto;

import blackjack.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserScoreDto {
    private final String name;
    private final List<CardDto> cards;
    private final int score;

    private UserScoreDto(String name, List<CardDto> cards, int score) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards.stream()
                .map(CardDto::getCardInfo)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getScore() {
        return score;
    }

    public static UserScoreDto from(User user) {
        List<CardDto> collect = user.getCards().getCards().stream()
                .map(CardDto::from)
                .collect(Collectors.toList());
        return new UserScoreDto(user.getName(), collect, user.getScore());
    }

}
