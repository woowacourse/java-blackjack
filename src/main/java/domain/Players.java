package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {
    private final List<Player> playerList;

    public Players(List<String> names, List<Integer> betAmounts) {
        validateDuplicate(names);
        validateSize(names, betAmounts);
        playerList = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerList.add(new Player(names.get(i), betAmounts.get(i)));
        }
    }

    public List<Player> getGamePlayers() {
        return playerList;
    }

    public int getSize() {
        return playerList.size();
    }

    private void validateDuplicate(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    private void validateSize(List<String> names, List<Integer> betAmounts) {
        if (names.size() != betAmounts.size()) {
            throw new IllegalArgumentException("플레이어와 베팅 금액이 매칭되지 않습니다.");
        }
    }
}
