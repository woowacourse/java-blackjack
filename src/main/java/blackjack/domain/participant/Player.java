package blackjack.domain.participant;

public class Player extends Participant {
    private final int betting;
    public Player(final Name name, final int betting) {

        super(name);
        this.betting = betting;
    }

    public int getBetting(){return this.betting;}
}
