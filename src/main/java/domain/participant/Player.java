package domain.participant;

import domain.Money;
import domain.result.BlackjackResult;

public class Player extends Participant {

    private final String name;
    private final Money betAmount;

    private Player(final String name, final Money betAmount) {
        super();
        this.name = name;
        this.betAmount = betAmount;
    }

    public static Player of(final String name, final Money betAmount) {
        return new Player(name, betAmount);
    }

    public String getName() {
        return name;
    }

    public void increaseTotalAmount(double ratio) {
        increaseAmount((int) Math.round(betAmount.getAmount() * ratio));
    }

    public int getBetAmount() {
        return betAmount.getAmount();
    }

    public BlackjackResult getBlackjackResult(Dealer dealer) {
        return BlackjackResult.getPlayerResult(dealer, this);
    }
}
