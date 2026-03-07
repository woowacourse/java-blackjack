package domain.player;

import domain.MatchResult;

public class Gambler extends Player {


    public Gambler(String name) {
        super(new Name(name));
    }

    public MatchResult getResult(int dealerScore) {
        return MatchResult.of(score(), dealerScore);
    }

}
