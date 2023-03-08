package domain;

import java.util.Objects;

public class Player extends Participant {
    private static final String UNAVAILABLE_NAME = "'%s'라는 이름은 사용할 수 없습니다.";

    private BettingMoney betAmount;

    private Player(Name name) {
        super(name);
    }

    private Player(Name name, BettingMoney betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public static Player from(Name name) {
        validateNameIsNotSameDealer(name);

        return new Player(name);
    }

    public static Player of(Name name, BettingMoney betAmount) {
        validateNameIsNotSameDealer(name);

        return new Player(name, betAmount);
    }

    private static void validateNameIsNotSameDealer(Name name) {
        if (DEALER_NAME.equals(name.getName())) {
            throw new IllegalArgumentException(String.format(UNAVAILABLE_NAME, DEALER_NAME));
        }
    }

    // TODO: 2023-03-07  배팅 금액을 업데이트 할 필요가 있을까 ?
//    public void updateBetAmount(Result result) {
//        betAmount = result.updateBalance(betAmount, cards.isBlackjack());
//    }

    public int getProfit(Result result) {
        int currentMoney = result.payOut(betAmount, this.isBlackjack()).getMoney();
        int bettingMoney = betAmount.getMoney();

        return currentMoney - bettingMoney;
    }

    public int getMoney() {
        return betAmount.getMoney();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
