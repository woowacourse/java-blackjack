package domain.participant;

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
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 빈 값이 아니여야 합니다.");
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
