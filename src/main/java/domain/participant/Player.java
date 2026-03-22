package domain.participant;

import domain.card.Cards;
import domain.state.Outcome;
import domain.state.HandState;
import domain.state.TurnState;

public final class Player extends Participant {
    private final Name name;
    private final Betting betting;
    private TurnState turnState;

    public Player(Name name, Betting betting) {
        super();
        this.name = name;
        this.betting = betting;
        this.turnState = TurnState.HITTING;
    }

    public Player(String name, int betting) {
        this(new Name(name), new Betting(betting));
    }

    public void stand() {
        this.turnState = TurnState.FINISHED;
    }

    public void hit(Cards cards) {
        drawCard(cards);
        if (getHandState() != HandState.STAND) {
            stand();
        }
    }

    @Override
    public TurnState getTurnState() {
        if (getHandState() != HandState.STAND) {
            return TurnState.FINISHED;
        }
        return turnState;
    }

    public String getName() {
        return name.getName();
    }

    public int applyOutcome(Outcome outcome){
        final int profit = (int) (betting.getBetting() * outcome.winningCoefficient());
        addBalance(profit);
        return profit;
    }
}
