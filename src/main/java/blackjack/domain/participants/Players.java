package blackjack.domain.participants;

import blackjack.domain.ResultType;
import blackjack.dto.GameResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final String DELIMITER = ",";
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players valueOf(String unParsedNames) {
        List<Player> parsedPlayers = Arrays.stream(unParsedNames.split(DELIMITER, -1))
            .map(name -> new Player(name))
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
        Map<Player, ResultType> result = new LinkedHashMap<>();
        players.forEach(player -> result.put(player, player.match(dealer)));

        return new GameResult(result);
    }

    public Player nextPlayerToPrepare() {
        return players.stream()
            .filter(Player::isContinue)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("이미 모든 플레이어가 준비가 되었습니다."));
    }

    public boolean isNotPrepared() {
        return !players.stream().noneMatch(Player::isContinue);
    }

    public List<Player> unwrap() {
        return new ArrayList<>(players);
    }
}
