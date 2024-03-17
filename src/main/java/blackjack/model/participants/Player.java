package blackjack.model.participants;

import blackjack.model.results.Result;
import blackjack.model.state.State;
import blackjack.vo.Money;
import blackjack.vo.Name;

public class Player extends Participant {
    private final Name name;
    private Money betAmount = new Money();

    public Player(String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean canHit() {
        return getState().isHit();
    }

    public void betMoney(Money betMoney) {
        betAmount = betMoney;
    }

    public Money calculateProfit(Result result) {
        return result.calculateProfit(betAmount);
    }

    public Result evaluateResult(State otherState) {
        return getState().determineResult(otherState);
    }

    public String getName() {
        return name.value();
    }
}
