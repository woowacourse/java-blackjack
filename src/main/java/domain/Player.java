package domain;


public class Player extends Participant{
    private final Name name;
    private final Betting betting;

    private Player(Name name, Betting betting, Hand hand) {
        super(hand);
        this.betting = betting;
        this.name = name;
    }

    public static Player of(Name name, Betting betting, Hand hand) {
        return new Player(name, betting, hand);
    }

    public Name getName() {
        return name;
    }

    public int getBettingAmount() {
        return betting.amount();
    }
}
