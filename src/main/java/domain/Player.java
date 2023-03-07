package domain;

import java.util.Objects;

public class Player extends Participant {
    private static final String UNAVAILABLE_NAME = "'%s'라는 이름은 사용할 수 없습니다.";

    private BetAmount betAmount;

    private Player(Name name) {
        super(name);
    }

    private Player(Name name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public static Player from(Name name) {
        validateNameIsNotSameDealer(name);

        return new Player(name);
    }

    public static Player of(Name name, BetAmount betAmount) {
        validateNameIsNotSameDealer(name);

        return new Player(name, betAmount);
    }

    private static void validateNameIsNotSameDealer(Name name) {
        if (DEALER_NAME.equals(name.getName())) {
            throw new IllegalArgumentException(String.format(UNAVAILABLE_NAME, DEALER_NAME));
        }
    }

    public void updateBetAmount(Result result) {
        betAmount = result.updateBalance(betAmount, cards.isBlackjack());
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
