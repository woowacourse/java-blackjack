package domain.participant;

import domain.Money;
import domain.card.Card;
import java.util.List;

public class Player extends Participant {
    private final String name;
    private Money money;

    public Player(String name, List<Card> hand) {
        super(hand);
        validateEmptyNames(name);
        this.name = name;
    }

    public void addMoney(Money money) {
        this.money = money;
    }

    private void validateEmptyNames(String playerName) {
        if (playerName.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 플레이어의 이름은 빈 값이 아니여야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
