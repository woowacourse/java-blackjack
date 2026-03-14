package blackjack.model.participant;

import blackjack.model.game.BlackjackResult;
import blackjack.model.state.BlackjackState;

public class Player extends Participant {

    private final Name name;
    private final Bet bet;

    public Player(Name name, Bet bet) {
        super();
        this.name = name;
        this.bet = bet;
    }

    public Player(Name name, Bet bet, BlackjackState state) {
        super(state);
        this.name = name;
        this.bet = bet;
    }

    public String getName() {
        return name.get();
    }

    public double calculateProfit(BlackjackState dealerState) {
        BlackjackResult result = BlackjackResult.judge(this.state, dealerState);

        return result.calculateProfit(bet, state.getEarningRate());
    }
}
