package domain.player;

import domain.MatchResult;

public class Gambler extends Player {

    private final Name name;
    private final Betting betting;

    public Gambler(String name, int amount) {
        super();
        this.name = new Name(name);
        this.betting = new Betting(amount);
    }

    public String getName() {
        return name.name();
    }

    public int calculateReward(MatchResult matchResult) {
        return (int) (betting.amount() * matchResult.getRate());
    }

}
