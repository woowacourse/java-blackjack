package domain.participant;

import domain.card.Cards;
import domain.state.Outcome;

public final class Player extends Participant {
    private static final int BLACKJACK_SCORE = 21;

    private final Name name;
    private final Betting betting;
    private boolean stand;

    public Player(Name name, Betting betting) {
        super();
        this.name = name;
        this.betting = betting;
        this.stand = false;
    }

    public Player(String name, int betting) {
        this(new Name(name), new Betting(betting));
    }

    public boolean canHit() {
        return !stand && canDraw();
    }

    public void stand() {
        this.stand = true;
    }

    public void hit(Cards cards) {
        drawCard(cards);
        if (getScore() == BLACKJACK_SCORE) {
            stand();
        }
    }

    public String getName() {
        return name.getName();
    }

    public void applyOutcome(Outcome outcome){
        addBalance((int) (betting.getBetting() * outcome.winningCoefficient()));
    }
}
