package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private final Name name;
    private final List<Card> cards;
    private final int value;

    public UserDto(User user) {
        name = user.getName();
        cards = new ArrayList<>(user.getCards());
        value = user.getValue();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getValue() {
        return value;
    }
}
