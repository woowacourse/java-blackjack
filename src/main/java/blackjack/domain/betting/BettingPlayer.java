package blackjack.domain.betting;

import blackjack.domain.participant.Participant;
import blackjack.domain.state.State;

public class BettingPlayer {

    private final Participant player;
    private final Money bettingMoney;

    private BettingPlayer(Participant player, Money bettingMoney) {
        this.player = player;
        this.bettingMoney = bettingMoney;
    }

    public static BettingPlayer of(Participant player, String amount) {
        return new BettingPlayer(player, new Money(amount));
    }

    public int calculateProfit(Participant dealer) {
        State state = player.getState();
        double earningRate = state.earningRate(dealer.getState());
        return bettingMoney.multiple(earningRate);
    }

    public String getName() {
        return player.getName();
    }
}
