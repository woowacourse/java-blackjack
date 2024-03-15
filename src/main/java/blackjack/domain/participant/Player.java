package blackjack.domain.participant;

import blackjack.domain.common.Money;

public class Player extends Participant {

    private static final int MIN_PLAYER_MONEY_AMOUNT = 1;

    private final Money money;

    public Player(String name, Money money) {
        super(name);

        if (!money.isGreaterEqualThan(MIN_PLAYER_MONEY_AMOUNT)) {
            String message = String.format("사용자의 초기 돈은 최소 %d 이상입니다.", MIN_PLAYER_MONEY_AMOUNT);

            throw new IllegalArgumentException(message);
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
