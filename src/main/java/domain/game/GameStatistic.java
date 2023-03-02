package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

import java.util.Map;

public class GameStatistic {

    private final Dealer dealer;
    private final Map<Participant, PlayerResult> resultMap;

    public GameStatistic(final Dealer dealer, final Map<Participant, PlayerResult> resultMap) {
        this.dealer = dealer;
        this.resultMap = resultMap;
    }

    public Dealer dealer() {
        return dealer;
    }

    public Map<Participant, PlayerResult> resultMap() {
        return resultMap;
    }
}
