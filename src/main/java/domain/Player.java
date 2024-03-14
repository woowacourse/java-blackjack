package domain;

import domain.constant.GamerResult;
import java.math.BigDecimal;

public class Player extends Gamer {
    private final Name name;
    private final Money money;
    public Player(Name name) {
        this.money = new Money(BigDecimal.valueOf(0));
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

    public Money betting(Dealer dealer) {
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
        return money.multiply(BigDecimal.valueOf(-1.0));
    }

    private Money gainMoney() {
        if (isBlackJack()){
            return money.multiply(BigDecimal.valueOf(1.5));
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
