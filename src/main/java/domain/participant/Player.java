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

    public int finalProfit(Dealer dealer) {
        if (dealer.isBlackjack() && isBlackjack()) { return money.draw(); }
        if (isBlackjack()) { return money.blackjack(); }
        if (isBust()) { return money.bust(); }
        if (dealer.isBust()) { return money.win(); }
        if (calculateScore() > dealer.calculateScore()) { return money.win(); }
        if (calculateScore() == dealer.calculateScore()) { return money.draw(); }
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

    public int getMoney() {
        return money.getBetAmount();
    }
}
