package model.money;

public class Bet {

    private static final int MINIMUM_BET = 10_000;

    private final long money;

    public Bet(final long money) {
        validateBet(money);
        this.money = money;
    }

    public Bet(String bet) {
        this(Long.parseLong(bet));
    }

    private void validateBet(final long money) {
        if (money < MINIMUM_BET) {
            throw new IllegalArgumentException(String.format("배팅은 %s원 초과로 해야합니다.", MINIMUM_BET));
        }
    }

    public Bet add(final Bet bet) {
        return new Bet(this.money + bet.money);
    }

    @Override
    public String toString() {
        return "Money{" +
                "money=" + money +
                '}';
    }

    public Long getMoney() {
        return money;
    }
}
