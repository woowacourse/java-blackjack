package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Players {

    private static final String DELIMITER = ",";
    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 8;
    private final List<Player> playersList;

    public Players(List<Player> players) {
        this.playersList = new ArrayList<>(players);
    }

    public static Players valueOf(String unParsedNames) {
        List<Player> parsedPlayers = Arrays.stream(unParsedNames.split(DELIMITER))
                .map(name -> new Player(name.trim()))
                .collect(Collectors.toList());
        validateDuplication(parsedPlayers);
        validatePlayersCount(parsedPlayers);
        return new Players(parsedPlayers);
    }

    private static void validateDuplication(List<Player> players) {
        if (players.size() != new HashSet<>(players).size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    private static void validatePlayersCount(List<Player> parsedPlayers) {
        if (parsedPlayers.size() < MINIMUM_PLAYERS || parsedPlayers.size() > MAXIMUM_PLAYERS) {
            throw new IllegalArgumentException(String.format("플레이어 수는 %d명 이상, %d명 이하여합니다.", MINIMUM_PLAYERS, MAXIMUM_PLAYERS));
        }
    }

    public GameResult match(Dealer dealer) {
        Map<Player, ResultType> result = new HashMap<>();
        playersList.forEach(player -> result.put(player, player.match(dealer)));

        return new GameResult(result);
    }

    public List<Player> unwrap() {
        return new ArrayList<>(playersList);
    }
}
