package domain;

import static util.ExceptionConstants.ERROR_HEADER;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Players() {
        this(Collections.emptyList());
    }

    public void addAll(List<Player> players) {
        this.players.addAll(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public ProfitResults calculateProfitResults(Dealer dealer) {
        Map<Player, Profit> profitResults = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.calculateProfit(dealer.getHand())
                ));
        return new ProfitResults(profitResults);
    }

    public List<String> getAllPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.isEqualName(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_HEADER + "해당 이름을 가진 플레이어가 없습니다."));
    }
}
