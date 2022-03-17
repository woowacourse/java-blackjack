package blackjack.domain.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Results {

    private final Map<Player, MatchResult> results;

    public Results(Map<Player, MatchResult> results) {
        this.results = results;
    }

    public static Results from(Players players) {
        return new Results(competeDealerWithPlayers(players));
    }

    private static Map<Player, MatchResult> competeDealerWithPlayers(Players players) {
        Map<Player, MatchResult> results = new LinkedHashMap<>();
        Player dealer = players.getPlayers()
                .stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow();
        for (Player player : players.getPlayers()) {
            scoreResultIfGuest(dealer, player, results);
        }
        return results;
    }

    private static void scoreResultIfGuest(Player dealer, Player guest, Map<Player, MatchResult> results) {
        if (guest.isDealer()) {
            return;
        }
        scoreEachGuest(dealer, guest, results);
    }

    private static void scoreEachGuest(Player dealer, Player guest, Map<Player, MatchResult> results) {
        Match result = Match.findWinner(guest, dealer);
        Match dealerResult = result.getDealerResult();
        addResult(dealer, dealerResult, results);
        addResult(guest, result, results);
    }

    private static void addResult(Player player, Match result, Map<Player, MatchResult> results) {
        results.put(player, results.getOrDefault(player, new MatchResult()).addMatchResult(result));
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(results.keySet());
    }

    public MatchResult getResult(Player player) {
        return results.get(player);
    }
}
