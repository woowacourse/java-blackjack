package domain.player;

public class Gambler extends Player {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Name name;
    private final Betting betting;

    public Gambler(String name, int amount) {
        super();
        this.name = new Name(name);
        this.betting = new Betting(amount);
    }

    public int calculateReward(double rate) {
        return (int) (betting.amount() * rate);
    }

    @Override
    public String getName() {
        return name.name();
    }

    @Override
    protected int getInitialCardCount() {
        return INITIAL_CARD_COUNT;
    }
}
