package domain.participants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final String INVALID_NAME = "중복된 이름입니다.";

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        validateDuplicatedName(names);
        addPlayer(names);
    }

    private void validateDuplicatedName(List<String> splitedName) {
        if (splitedName.size() != splitedName.stream().distinct().count()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    private void addPlayer(List<String> splitedName) {
        players.add(new Dealer());
        for (String name : splitedName) {
            players.add(new Player(name));
        }
    }

    public Dealer findDealer() {
        return (Dealer) players.get(0);
    }

    public List<Player> getPlayersWithOutDealer() {
        return players.stream().filter(s -> !s.equals(findDealer()))
                .collect(Collectors.toList());
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
