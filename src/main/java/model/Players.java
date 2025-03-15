package model;

import java.util.List;
import java.util.Map;

public final class Players {
    private final List<Player> players;

    public Players(Map<Name, Bet> registerInput) {
        this.players = registerInput.entrySet().stream()
                .map(registerEntry -> new Player(registerEntry.getKey(), registerEntry.getValue()))
                .toList();
    }
}
