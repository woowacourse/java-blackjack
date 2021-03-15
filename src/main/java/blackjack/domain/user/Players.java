package blackjack.domain.user;

import blackjack.domain.MatchRule;
import blackjack.domain.result.Results;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public Results generateResultsMapAgainstDealer(Dealer dealer) {
        return this.players.stream()
                .collect(collectingAndThen(toMap(player -> player,
                        player -> MatchRule.getMatchResult(player, dealer)),
                        Results::of));
    }

    public List<Player> players() {
        return Collections.unmodifiableList(this.players);
    }
}
