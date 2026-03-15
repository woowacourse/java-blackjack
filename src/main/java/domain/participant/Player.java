package domain.participant;

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

    public int finalProfit(Participant dealer) {
        if (dealer.isBlackjack() && isBlackjack()) { return money.getBack(); }
        if (isBlackjack()) { return money.earnOnePointFiveTimes(); }
        if (isBust()) { return money.lose(); }
        if (dealer.isBust()) { return money.earn(); }
        if (calculateScore() > dealer.calculateScore()) { return money.earn(); }
        if (calculateScore() == dealer.calculateScore()) { return money.getBack(); }
        return money.lose();
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
