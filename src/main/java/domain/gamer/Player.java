package domain.gamer;

import domain.deck.Card;
import domain.profit.Profit;
import domain.state.State;
import java.util.List;

public class Player extends Gamer {

    private final BetAmount betAmount;

    public Player(final Nickname nickname, final State state, final BetAmount betAmount) {
        super(nickname, state);
        this.betAmount = betAmount;
    }

    public Double calculateProfit(final Profit profit) {
        return betAmount.calculateProfit(profit);
    }

    @Override
    public List<Card> getInitialCards() {
        final Hand hand = getHand();
        return hand.getCards();
    }
}
