package blackjack.model.participants;

import blackjack.model.state.BlackJack;
import blackjack.model.state.Bust;
import blackjack.model.state.Hit;
import blackjack.model.state.Stand;
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
        return getState() instanceof Hit;
    }

    public void betMoney(Money betMoney) {
        betAmount = betMoney;
    }

    public Money evaluateProfit(State otherState) {
        if (isBlackJack(getState()) && isBlackJack(otherState)) {
            return new Money();
        }
        if (isStand(getState()) && isNotBust(otherState)) {
            return compareState(otherState, betAmount);
        }
        return getState().calculateProfit(betAmount);
    }

    private boolean isStand(State state) {
        return state instanceof Stand;
    }

    private boolean isNotBust(State otherState) {
        return !(otherState instanceof Bust);
    }

    private boolean isBlackJack(State state) {
        return state instanceof BlackJack;
    }

    private Money compareState(State otherState, Money betAmount) {
        int score = getScore();
        if (score > otherState.getScore()) {
            return getState().calculateProfit(betAmount);
        }
        if (score < otherState.getScore()) {
            return getState().calculateProfit(new Money(-betAmount.value()));
        }
        return new Money();
    }

    public String getName() {
        return name.value();
    }
}
