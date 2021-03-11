package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.*;

public class Players {

    private static final int MINIMUM_PLAYERS = 2;
    private static final int MAXIMUM_PLAYERS = 8;
    private final List<Player> playersList;

    public Players(List<Player> players) {
        validateDuplication(players);
        validatePlayersCount(players);
        this.playersList = new ArrayList<>(players);
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
        Map<Player, ResultType> result = new LinkedHashMap<>();
        playersList.forEach(player -> result.put(player, player.match(dealer)));

        return new GameResult(result);
    }

    public List<Player> unwrap() {
        return new ArrayList<>(playersList);
    }
}
