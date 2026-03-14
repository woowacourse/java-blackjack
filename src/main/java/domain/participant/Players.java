package domain.participant;

import domain.GameResult;
import domain.Money;
import domain.WinningStatus;
import dto.PlayerResultInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    public static final int PLAYER_THRESHOLD = 5;
    public static final String ERROR_PLAYER_COUNT_OVER = "[ERROR] 플레이어의 수는 5명 이하여야 합니다.";
    public static final String ERROR_PLAYERS_NAME_DUPLICATION = "[ERROR] 참가자의 이름이 중복됩니다.";

    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        this(names, new Money(0));
    }

    public Players(List<String> names, Money initialProfit) {
        for (String name : names) {
            players.add(new Player(name, initialProfit));
        }
        validatePlayerCount(players);
        validateDuplicatedName(players);
    }


    public List<String> getPlayerNames() {
        return players.stream()
                .map(player -> player.name())
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void applyRoundResults(GameResult gameResult) {
        Map<String, WinningStatus> statuses = gameResult.getPlayerWinningStatus();

        for (Player player : players) {
            WinningStatus status = statuses.get(player.name());
            player.applyRoundResult(status);
        }
    }

    public List<PlayerResultInfo> resultInfos() {
        return players.stream()
                .map(player -> new PlayerResultInfo(player.name(), player.profit()))
                .toList();
    }

    public int totalProfit() {
        return players.stream()
                .mapToInt(Player::profit)
                .sum();
    }

    private void validatePlayerCount(List<Player> players) {
        if (players.size() > PLAYER_THRESHOLD) {
            throw new IllegalArgumentException(ERROR_PLAYER_COUNT_OVER);
        }
    }

    private void validateDuplicatedName(List<Player> players) {
        Set<String> namesSet = players.stream()
                .map(player -> player.name())
                .collect(Collectors.toSet());

        if (namesSet.size() != players.size()) {
            throw new IllegalArgumentException(ERROR_PLAYERS_NAME_DUPLICATION);
        }
    }
}
