package blackjack.domain.user;

import blackjack.domain.MatchRule;
import blackjack.domain.dto.Results;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.*;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(Names names) {
        return new Players(names.stream()
                .map(Player::new)
                .collect(toList()));
    }

    public Results generateResultsMapAgainstDealer(Dealer dealer) {
        return this.players.stream()
                .collect(collectingAndThen(toMap(player -> player,
                        player -> MatchRule.getMatchResult(player, dealer)),
                        Results::new));
    }

    public List<Player> players() {
        return Collections.unmodifiableList(this.players);
    }
}
