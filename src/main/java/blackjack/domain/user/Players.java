package blackjack.domain.user;

import blackjack.domain.PlayerResult;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players create(Map<String, Bet> playerBets, Deck deck) {
        return playerBets.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue(), deck.drawInitCards()))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Players::new
                ));
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Map<Player, PlayerResult> getStatistics(Dealer dealer) {
        Map<Player, PlayerResult> result = new LinkedHashMap<>();
        for (Player player : players) {
            result.put(player, PlayerResult.valueOf(dealer, player));
        }
        return result;
    }
}
