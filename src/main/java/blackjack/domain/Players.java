package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Players {

    private static final String DELIMITER = ",";
    private final Queue<Player> players;

    public Players(List<Player> players) {
        this.players = new LinkedList<>(players);
    }

    public static Players valueOf(String unParsedNames) {
        List<Player> parsedPlayers = Arrays.stream(unParsedNames.split(DELIMITER))
            .map(Player::new)
            .collect(Collectors.toList());
        validateDuplication(parsedPlayers);
        return new Players(parsedPlayers);
    }

    private static void validateDuplication(List<Player> players) {
        if (players.size() != new HashSet<>(players).size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public GameResult match(Dealer dealer) {
        Map<Player, ResultType> result = new HashMap<>();
        players.stream().forEach(player -> result.put(player, player.match(dealer)));

        return new GameResult(result);
    }

    public Player pop() {
        return players.poll();
    }

    public void push(Player player) {
        players.add(player);
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public List<Player> unwrap() {
        return new ArrayList<>(players);
    }
}
