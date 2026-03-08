package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final String DEALER_NAME = "딜러";

    private final List<Player> playerList;

    public Players(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 최소 1명이어야 합니다.");
        }
        playerList = new ArrayList<>();
        playerList.add(new Player(DEALER_NAME));
        for (String name : names) {
            playerList.add(new Player(name));
        }
    }

    public int getSize() {
        return playerList.size();
    }
}
