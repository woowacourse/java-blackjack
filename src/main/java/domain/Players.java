package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private static final String INVALID_NAME = "중복된 이름입니다.";

    private List<Player> players = new ArrayList<>();

    public Players(String names) {
        List<String> splitedName = splitName(names);
        validateDuplicatedName(splitedName);
        for (String name : splitName(names)) {
            players.add(new Player(name));
        }
    }

    private void validateDuplicatedName(List<String> splitedName) {
        if (splitedName.size() != splitedName.stream().distinct().count()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    private List<String> splitName(String names) {
        return Arrays.asList(names.split(","));
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(s -> s.getName().getName())
                .collect(Collectors.toList());
    }

    public void addCard(int index, Card card) {
        players.get(index).addCard(card);
    }

    public Map<String, List<String>> getInfo() {
        Map<String, List<String>> info = new HashMap<>();
        for (Player player : players) {
            info.put(player.getName().getName(), player.getCards());
        }
        return info;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCardsSum(String playerName) {
        return players.stream()
                .filter(s -> s.getName().getName() == playerName)
                .findAny()
                .get()
                .getCardsSum();

    }
}
