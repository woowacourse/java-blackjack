package domain;

import exception.CustomException;
import java.util.ArrayList;
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
        this(new ArrayList<>());
    }

    public void addAll(List<Player> players) {
        this.players.addAll(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
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
                .orElseThrow(() -> new CustomException("해당 이름을 가진 플레이어가 없습니다."));
    }

    public ProfitResults calculateProfitResults(Hand dealerHand) {
        Map<Player, Profit> profitResults = players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.calculateProfit(dealerHand)
                ));
        return new ProfitResults(profitResults);
    }
}
