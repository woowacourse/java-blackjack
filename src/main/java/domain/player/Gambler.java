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

    public long calculateReward(MatchResult matchResult) {
        return matchResult.calculateReward(betting.amount());
    }

    @Override
    public String getName() {
        return name.name();
    }

}
