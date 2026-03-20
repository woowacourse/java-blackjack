package domain.participant;

import static exception.ErrorMessage.PLAYER_NAME_IS_EMPTY;

import domain.Money;
import domain.card.Cards;

public class Player extends Participant {
    private final String name;
    private final Money money;

    public Player(Cards cards, String name, Money money) {
        super(cards);
        validateEmptyNames(name);
        this.name = name;
        this.money = money;
    }

    private void validateEmptyNames(String playerName) {
        if (playerName.isBlank()) {
            throw new IllegalArgumentException(PLAYER_NAME_IS_EMPTY.getMessage());
        }
    }

    public boolean canHit() {
        return !isBust() && !isBlackjack();
    }

    public String getName() {
        return name;
    }

    public Money getMoney() {
        return money;
    }
}
