package service;

import domain.Player;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BlackjackService {

    public List<Player> createPlayers(List<String> names) {
        validatePlayerNames(names);
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        return players;
    }

    private void validatePlayerNames(List<String> names) {
        validatePlayerCount(names);
        validatePlayerCount(names.size());
    }

    private void validatePlayerCount(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 이름은 중복 되어선 안됩니다.");
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (!(2 <= playerCount && playerCount <= 8)) {
            throw new IllegalArgumentException("[ERROR] 게임 참가자의 수는 2~8명 사이여야 합니다.");
        }
    }
}
