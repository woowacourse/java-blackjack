package domain.user;

import java.util.Arrays;

import domain.result.ResultType;
import domain.rule.PlayerResultRule;

public class Player extends User {

    private Money money;

    public Player(String name, int money) {
        super(name);
        this.money = new Money(money);
    }

    @Override
    protected boolean isAvailableToDraw() {
        return !cards.areBust() && !cards.areBlackJack() && !cards.areBlackJackPoint();
    }

    public ResultType decideResultType(Dealer dealer) {
        return Arrays.stream(PlayerResultRule.values())
                .filter(rule -> rule.condition(this, dealer))
                .findFirst()
                .orElseThrow(() -> new AssertionError("게임 규칙이 올바르지 않습니다."))
                .getResultType();
    }
}
