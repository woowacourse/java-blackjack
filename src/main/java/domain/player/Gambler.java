package domain.player;

import domain.MatchResult;

public class Gambler extends Player {

    private final Name name;

    public Gambler(String name) {
        super();
        this.name = new Name(name);
    }

    public String getName() {
        return name.name();
    }

    public MatchResult getResult(int dealerScore) {
        return MatchResult.of(score(), dealerScore);
    }

}
