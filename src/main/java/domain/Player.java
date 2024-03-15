package domain;

import domain.constant.GamerResult;

public class Player extends Gamer {

    private static final String BLACKJACK_BONUS_RATIO = "1.5";

    private final Name name;
    private final Money money;

    Player(Name name) {
        this.money = new Money("0");
        this.name = name;
    }

    public Player(Name name, Money money) {
        this.money = money;
        this.name = name;
    }

    @Override
    public boolean canHit() {
        return !this.isBust();
    }

    public Money bet(Dealer dealer) {
        GamerResult result = judge(dealer);
        if (GamerResult.WIN.equals(result)) {
            return gainMoney();
        }
        if (GamerResult.LOSE.equals(result)) {
            return loseMoney();
        }
        return money;
    }

    private Money loseMoney() {
        return money.negative();
    }

    private Money gainMoney() {
        if (isBlackJack()) {
            return money.multiply(BLACKJACK_BONUS_RATIO);
        }
        return money;
    }

    public boolean hasName(Name comparedName) {
        return name.equals(comparedName);
    }

    public Name getName() {
        return name;
    }
}
