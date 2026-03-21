package domain.participant;

import static exception.ErrorMessage.PLAYER_NAME_IS_EMPTY;

import domain.card.Cards;
import domain.rule.State;

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
        return !state.isFinished();
    }

    public double profit(State dealerState) {
        return state.profit(money.getBetAmount(), dealerState);
    }

    public String getName() {
        return name;
    }
}
