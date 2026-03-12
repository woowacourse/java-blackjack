package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {
    private final List<Player> playerList;

    public Players(List<String> names,List<Integer> betAmounts) {
        validateDuplicate(names);
        //입력값이 유효 범위인지 검증하는 메서드 필요
        //이름 = 베팅 금액 일치 여부 검증
        playerList = new ArrayList<>();
        for (String name : names) {
            playerList.add(new Player(name, betAmounts.get(names.indexOf(name))));
        }
    }

    private void validateDuplicate(List<String> names) {
        if (names.size() != new HashSet<>(names).size()) {
            throw new IllegalArgumentException("중복된 이름이 존재합니다.");
        }
    }

    public List<Player> getGamePlayers() {
        return playerList;
    }

    public int getSize() {
        return playerList.size();
    }
}
