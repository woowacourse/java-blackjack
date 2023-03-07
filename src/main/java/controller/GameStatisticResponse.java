package controller;

import domain.player.Dealer;
import domain.player.DealerCompeteResult;
import domain.player.Gambler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatisticResponse {

    private final Dealer dealer;
    private final List<Gambler> gamblers;
    private final Map<Gambler, DealerCompeteResult> dealerResultPerPlayer;

    public GameStatisticResponse(final Dealer dealer, final List<Gambler> gamblers, final Map<Gambler, DealerCompeteResult> dealerResultPerPlayer) {
        this.dealer = dealer;
        this.gamblers = new ArrayList<>(gamblers);
        this.dealerResultPerPlayer = dealerResultPerPlayer;
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Gambler> players() {
        return new ArrayList<>(gamblers);
    }

    public Map<Gambler, DealerCompeteResult> dealerResultPerPlayer() {
        return dealerResultPerPlayer;
    }
}
