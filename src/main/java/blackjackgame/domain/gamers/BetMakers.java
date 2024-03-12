package blackjackgame.domain.gamers;

import java.util.List;

public class BetMakers {
    private List<BetMaker> betMakerGamers;

    public BetMakers(List<BetMaker> betMakerGamers) {
        this.betMakerGamers = betMakerGamers;
    }

    public List<BetMaker> getBetMakerGamers() {
        return betMakerGamers;
    }
}
