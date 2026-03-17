package domain.participant;

import domain.card.Cards;
import domain.state.Outcome;

public final class Player extends Participant {
    private final Name name;
    private Betting betting;

    public Player(Name name, Betting betting) {
        super();
        this.name = name;
        this.betting = betting;
    }

    public Player(String name, int betting) {
        this(new Name(name), new Betting(betting));
    }

    public void hit(final Cards cards) {
        drawCard(cards);
    }

    public boolean canHit() {
        return getHandState().isHit();
    }

    public String getName() {
        return name.getName();
    }

    public void updateBalance(Outcome outcome){
        updateBalance((int) (betting.getBettingMoney() * outcome.winningCoefficient()));
    }
}
