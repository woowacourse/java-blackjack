package blackjack.domain.money;

import blackjack.exception.BlackJackException;

public class Money {

    public static final String NEGATIVE_MONEY_MESSAGE = "마이너스 베팅을 할 수가 없습니다.";

    private int value;

    public Money(int betMoney) {
        checkNegative(betMoney);
        this.value = betMoney;
    }

    private void checkNegative(int betMoney) {
        if (betMoney < 0) {
            throw new BlackJackException(NEGATIVE_MONEY_MESSAGE);
        }
    }

    public int getValue() {
        return this.value;
    }

}
