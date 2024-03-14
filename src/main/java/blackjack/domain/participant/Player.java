package blackjack.domain.participant;

import blackjack.domain.common.Money;

public class Player extends Participant {

    private final Money money;

    public Player(String name, Money money) {
        super(name);

        if (money.isNegative()) {
            throw new IllegalArgumentException("사용자의 초기 돈은 음수가 될 수 없습니다.");
        }

        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean isPlayable() {
        return !(isBust() || isBlackJackScore());
    }
}
