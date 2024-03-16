package domain.participant;

import domain.bet.BetAmount;
import domain.blackjack.WinStatus;
import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    public static final int INITIAL_REVEAL_COUNT = 2;
    private static final int BLACK_JACK_COUNT = 21;

    private final BetAmount betAmount;

    public Player(Name name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public Player(String name, double betAmount) {
        this(new Name(name), new BetAmount(betAmount));
    }

    public double profit(WinStatus winStatus) {
        return betAmount.getValue() * (winStatus.getProfitMultiplier());
    }

    @Override
    public boolean canHit() {
        return hands.calculateScore() <= BLACK_JACK_COUNT;
    }

    @Override
    public List<Card> revealCardOnInitDeal() {
        return hands.getValue(INITIAL_REVEAL_COUNT);
    }
}
