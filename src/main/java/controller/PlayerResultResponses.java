package controller;

import model.user.Player;
import model.user.Result;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class PlayerResultResponses {

    private final Map<String, String> playerResultResponses;

    public PlayerResultResponses(final Map<Player, Result> playerFinalResult) {
        this.playerResultResponses = playerFinalResult.keySet().stream()
                .collect(toMap(Player::getName, player -> playerFinalResult.get(player).getName()));
    }

    public Map<String, String> getPlayerResultResponses() {
        return new HashMap<>(playerResultResponses);
    }
}
