package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {
    private static final String DEALER_NAME = "딜러";

    private final List<Player> playerList;

    public Players(List<String> names) {
        validateDuplicate(names);
        playerList = new ArrayList<>();
        playerList.add(new Player(DEALER_NAME));
        for (String name : names) {
            playerList.add(new Player(name));
        }
    }

    private void validateDuplicate(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    public Player getDealer() {
        return playerList.get(0);
    }

    public List<Player> getGamePlayers() {
        return playerList.subList(1, playerList.size());
    }

    public int getSize() {
        return playerList.size();
    }
}
