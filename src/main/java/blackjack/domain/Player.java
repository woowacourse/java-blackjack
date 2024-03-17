package blackjack.domain;

public class Player extends Participant {

    private final Betting betting;

    public Player(String name, Betting betting) {
        super(name);

        this.betting = betting;
    }

    @Override
    public boolean canHit() {
        return calculate() < BLACKJACK_SCORE;
    }

    public int getBetting() {
        return this.betting.getBetting();
    }
}
