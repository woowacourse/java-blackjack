package domain.gamer;

import domain.deck.Card;
import domain.profit.Outcome;
import domain.state.State;
import java.util.List;

public class Player extends Gamer {

    private final BetAmount betAmount;

    public Player(final Nickname nickname, final State state, final BetAmount betAmount) {
        super(nickname, state);
        this.betAmount = betAmount;
    }

    public Double calculateProfit(final Outcome outcome) {
        return betAmount.calculateProfit(outcome);
    }

    @Override
    public List<Card> getInitialCards() {
        final Hand hand = getHand();
        return hand.getCards();
    }

    @Override
    public Nickname getNickname() {
        return super.getNickname();
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public int calculateSumOfRank() {
        return getHand().calculateSumOfRank();
    }

    public State getState() {
        return super.state;
    }

    public BetAmount getBetAmount() {
        return betAmount;
    }
}
