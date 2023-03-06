package domain.participants;

import domain.participants.Dealer;
import domain.participants.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private static final String INVALID_NAME = "중복된 이름입니다.";
    private static final String SPLIT_DELIMITER = ",";

    private final List<Player> players = new ArrayList<>();

    public Players(String names) {
        List<String> splitedName = splitName(names);
        validateDuplicatedName(splitedName);
        addPlayer(splitedName);
    }

    private void addPlayer(List<String> splitedName) {
        players.add(new Dealer());
        for (String name : splitedName) {
            players.add(new Player(name));
        }
    }

    private List<String> splitName(String names) {
        return Arrays.asList(names.split(SPLIT_DELIMITER));
    }

    private void validateDuplicatedName(List<String> splitedName) {
        if (splitedName.size() != splitedName.stream().distinct().count()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public Dealer findDealer() {
        return (Dealer) players.get(0);
    }

    public Map<String, List<String>> getInfo() {
        Map<String, List<String>> info = new LinkedHashMap<>();
        for (Player player : getPlayersWithOutDealer()) {
            info.put(player.getName(), player.getCards());
        }
        return info;
    }

    public List<Player> getPlayersWithOutDealer() {
        return players.stream().filter(s -> !s.getName().equals(findDealer().getName())).collect(Collectors.toList());
    }

    public int getCardsSum(String playerName) {
        return players.stream()
                .filter(s -> s.getName().equals(playerName))
                .findAny()
                .get()
                .getCardsSum();
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
