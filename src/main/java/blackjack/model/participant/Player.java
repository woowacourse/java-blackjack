package blackjack.model.participant;

import blackjack.model.game.BlackjackResult;
import blackjack.model.hand.Hand;

public class Player extends Participant {

    private final Name name;
    private final Bet bet;

    public Player(Name name, Bet bet) {
        super();
        this.name = name;
        this.bet = bet;
    }

    public Player(Name name, Bet bet, Hand hand) {
        super(hand);
        this.name = name;
        this.bet = bet;
    }

    public String getName() {
        return name.get();
    }

    public double calculateProfit(Hand dealerHand) {
        BlackjackResult result = BlackjackResult.judge(this.hand, dealerHand);

        return result.calculateProfit(bet, hand.getEarningRate());
    }
}
