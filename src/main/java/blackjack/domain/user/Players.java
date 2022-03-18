package blackjack.domain.user;

import blackjack.domain.card.Deck;
import blackjack.domain.PlayerResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players create(List<String> playerNames, List<Bet> bets, Deck deck) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), bets.get(i), deck.drawInitCards()));
        }
        return new Players(players);
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
