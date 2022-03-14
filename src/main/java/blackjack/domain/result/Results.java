package blackjack.domain.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Results {

    private final Players players;

    public Results(Players players) {
        this.players = players;
    }

    private final Map<Player, MatchResult> results = new LinkedHashMap<>();

    public void addResult(Player player, Match result) {
        results.put(player, results.getOrDefault(player, new MatchResult()).addMatchResult(result));
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(results.keySet());
    }

    public MatchResult getResult(Player player) {
        return results.get(player);
    }

    public void calculate() {
        Player dealer = players.getPlayers()
                .stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow();
        for (Player player : players.getPlayers()) {
            scoreResultIfGuest(dealer, player);
        }
    }

    private void scoreResultIfGuest(Player dealer, Player guest) {
        if (guest.isDealer()) {
            return;
        }
        scorePlayers(dealer, guest);
    }

    private void scorePlayers(Player dealer, Player guest) {
        Match result = Match.findWinner(guest, dealer);
        Match dealerResult = result.getDealerResult();
        addResult(dealer, dealerResult);
        addResult(guest, result);
    }
}
