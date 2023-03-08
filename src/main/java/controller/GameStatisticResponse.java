package controller;

import domain.player.Dealer;
import domain.player.GamblerCompeteResult;
import domain.player.Gambler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatisticResponse {

    private final Dealer dealer;
    private final List<Gambler> gamblers;
    private final Map<Gambler, GamblerCompeteResult> dealerResultPerGambler;

    public GameStatisticResponse(final Dealer dealer, final List<Gambler> gamblers, final Map<Gambler, GamblerCompeteResult> dealerResultPerGambler) {
        this.dealer = dealer;
        this.gamblers = new ArrayList<>(gamblers);
        this.dealerResultPerGambler = dealerResultPerGambler;
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Gambler> gamblers() {
        return new ArrayList<>(gamblers);
    }

    public Map<Gambler, GamblerCompeteResult> dealerResultPerGambler() {
        return dealerResultPerGambler;
    }
}
