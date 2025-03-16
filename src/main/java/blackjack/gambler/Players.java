package blackjack.gambler;

import blackjack.card.Cards;
import blackjack.constant.MatchResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public Map<Player, MatchResult> deriveResults(int dealerScore) {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.compareTo(dealerScore),
                        (x, y) -> y, LinkedHashMap::new
                ));
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<String> getPlayerNames() {
        return new ArrayList<>(players.stream()
                .map(Player::getUsername)
                .toList());
    }

    public void initializeHands(List<Cards> cards) {
        IntStream.range(0, cards.size())
                .forEach(i -> players.get(i).initializeHand(cards.get(i)));
    }

    public Player findPlayer(String playerName) {
        return players.stream()
                .filter(player -> player.getUsername().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 사용자를 찾을 수 없습니다."));
    }
}
