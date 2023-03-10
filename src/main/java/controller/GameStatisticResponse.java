package controller;

import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Participant;
import domain.player.Revenue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatisticResponse {

    private final Dealer dealer;
    private final List<Gambler> gamblers;
    private final Map<Participant, Revenue> participantRevenueMap;

    public GameStatisticResponse(final Dealer dealer,
                                 final List<Gambler> gamblers,
                                 final Map<Participant, Revenue> participantRevenueMap) {
        this.dealer = dealer;
        this.gamblers = new ArrayList<>(gamblers);
        this.participantRevenueMap = participantRevenueMap;
    }

    public Dealer dealer() {
        return dealer;
    }

    public List<Gambler> gamblers() {
        return new ArrayList<>(gamblers);
    }

    public Map<Participant, Revenue> participantRevenueMap() {
        return participantRevenueMap;
    }
}
