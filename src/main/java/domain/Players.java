package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private static final String INVALID_NAME = "중복된 이름입니다.";
    private static final String SPLIT_DELIMITER = ",";

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
        return Arrays.asList(names.split(SPLIT_DELIMITER));
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }

    public Map<String, List<Card>> getInformation() {
        Map<String, List<Card>> information = new LinkedHashMap<>();
        for (Player player : players) {
            information.put(player.getName(), player.getCards());
        }
        return information;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCardsSum(String playerName) {
        return players.stream()
                .filter(s -> s.getName() == playerName)
                .findAny()
                .get()
                .getCardsSum();
    }

}
