package blackjack.dto;

import java.util.List;
import java.util.Map;

public class UserCardsDto {
    private final Map<String, List<CardDto>> userNameAndCards;

    public UserCardsDto(Map<String, List<CardDto>> userNameAndCards) {
        this.userNameAndCards = userNameAndCards;
    }

    public Map<String, List<CardDto>> getUserNameAndCards() {
        return userNameAndCards;
    }
}
