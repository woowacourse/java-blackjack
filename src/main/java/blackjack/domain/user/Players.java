package blackjack.domain.user;

import blackjack.domain.MatchRule;
import blackjack.domain.ResultType;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> players) {
        return new Players(players.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public List<Player> players() {
        return Collections.unmodifiableList(this.players);
    }

    public Map<User, ResultType> generateResultsMapAgainstDealer(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> MatchRule.getMatchResult(player.getCards(), dealer.getCards())
                ));
    }
}
