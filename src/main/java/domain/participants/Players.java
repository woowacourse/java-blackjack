package domain.participants;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final String INVALID_NAME = "중복된 이름입니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validateDuplicatedName(players);
        this.players = players;
    }

    private void validateDuplicatedName(List<Player> splitedName) {
        if (splitedName.size() != splitedName.stream().map(s->s.getName()).distinct().count()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public Dealer findDealer() {
        return (Dealer) players.get(0);
    }

    public List<Player> getPlayersWithOutDealer() {
        return players.stream().filter(s -> !s.equals(findDealer()))
                .collect(Collectors.toList());
    }
}
