package blackjack.domain;

import blackjack.exception.BettingMoneyNegativeException;
import blackjack.exception.UserNameEmptyException;

import java.util.List;

public class Player extends User {
    private static final String PLAYER_NAME_EMPTY_EXCEPTION_MESSAGE = "플레이어의 이름은 공백일 수 없습니다.";
    private static final String BETTING_MONEY_NEGATIVE_EXCEPTION_MESSAGE = "배팅 금액으로는 양수만 가능합니다.";
    private static final int USER_INITIAL_CARDS_SIZE = 2;
    private static final int BETTING_MONEY_LOWER_BOUND = 0;

    private final int bettingMoney;

    public Player(String name, int bettingMoney) {
        validateEmpty(name);
        validateNegative(bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validateEmpty(String name) {
        if (name.isEmpty()) {
            throw new UserNameEmptyException(PLAYER_NAME_EMPTY_EXCEPTION_MESSAGE);
        }
    }

    private void validateNegative(int bettingMoney) {
        if (bettingMoney <= BETTING_MONEY_LOWER_BOUND) {
            throw new BettingMoneyNegativeException(BETTING_MONEY_NEGATIVE_EXCEPTION_MESSAGE);
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public List<Card> getInitialCards() {
        return cards.getCards()
                .subList(START_INDEX, USER_INITIAL_CARDS_SIZE);
    }

    @Override
    public boolean isReceivableOneMoreCard() {
        return cards.isStatusNone();
    }
}
