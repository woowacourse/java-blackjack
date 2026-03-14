package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerGroup;
import java.util.Map;
import java.util.stream.Collectors;

public record PlayerResults(Map<Player, GameResult> entries) {

    public static PlayerResults of(Dealer dealer, PlayerGroup playerGroup) {
        GameJudge judge = new GameJudge();
        Map<Player, GameResult> results = resultsOf(dealer, playerGroup, judge);
        return new PlayerResults(results);
    }

    private static Map<Player, GameResult> resultsOf(
        Dealer dealer, PlayerGroup playerGroup, GameJudge judge) {
        return playerGroup.players().stream()
            .collect(Collectors.toMap(
                player -> player,
                player -> judge.getResult(dealer, player)
            ));
    }

    @Override
    public Map<Player, GameResult> entries() {
        return Map.copyOf(entries);
    }
}
