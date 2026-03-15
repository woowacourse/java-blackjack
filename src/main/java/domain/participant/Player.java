package domain.participant;

import static exception.ErrorMessage.PLAYER_NAME_IS_EMPTY;

import domain.Money;
import domain.card.Card;
import java.util.List;

public class Player extends Participant {
    private final String name;
    private final Money money;

    public Player(List<Card> hand, String name, Money money) {
        super(hand);
        validateEmptyNames(name);
        this.name = name;
        this.money = money;
    }

    private void validateEmptyNames(String playerName) {
        if (playerName.isBlank()) {
            throw new IllegalArgumentException(PLAYER_NAME_IS_EMPTY.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }
}
