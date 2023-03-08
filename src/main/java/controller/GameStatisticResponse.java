package controller;

import domain.game.Revenue;
import domain.player.Dealer;
import domain.player.Gambler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatisticResponse {

    private final Dealer dealer;
    private final List<Gambler> gamblers;
    private final Map<Gambler, Revenue> gamblerRevenueMap;

    public GameStatisticResponse(final Dealer dealer,
                                 final List<Gambler> gamblers,
                                 final Map<Gambler, Revenue> gamblerRevenueMap) {
        this.dealer = dealer;
        this.gamblers = new ArrayList<>(gamblers);
        this.gamblerRevenueMap = gamblerRevenueMap;
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Gambler> gamblers() {
        return new ArrayList<>(gamblers);
    }

    public Map<Gambler, Revenue> gamblerRevenueMap() {
        return gamblerRevenueMap;
    }
}
