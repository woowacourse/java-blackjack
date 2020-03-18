package controller;

import model.Bet;

import java.util.Map;

public class PlayersData {
    private final Map<String, Bet> playersData;

    public PlayersData(Map<String, Bet> playersData) {
        this.playersData = playersData;
    }

}
