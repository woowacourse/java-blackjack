package model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class PlayersData {
    private final Map<String, Bet> playersData = new LinkedHashMap<>();

    public PlayersData(Map<String, Bet> playersData) {
        this.playersData.putAll(playersData);
    }

    public Bet getBet(String name) {
        return playersData.get(name);
    }

    public Set<String> getNames() {
        return playersData.keySet();
    }
}
