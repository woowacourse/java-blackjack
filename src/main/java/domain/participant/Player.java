package domain.participant;

import domain.state.Outcome;

public final class Player extends Participant {
    private final Name name;
    private final Betting betting;

    public Player(Name name, Betting betting) {
        super();
        this.name = name;
        this.betting = betting;
    }

    public Player(String name, int betting) {
        this(new Name(name), new Betting(betting));
    }

    public boolean canHit() {
        return getHandState().isHit() && canDraw();
    }

    public String getName() {
        return name.getName();
    }

    public void applyOutcome(Outcome outcome){
        addBalance((int) (betting.getBetting() * outcome.winningCoefficient()));
    }
}
