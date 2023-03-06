package domain.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultMaker {

    public Map<Player, Result> makePlayersResult(final Dealer dealer, final List<Player> players) {
        return players.stream()
            .collect(Collectors.toMap(player -> player,
                player -> Result.decide(player.getScore(), dealer.getScore()), (a, b) -> b,
                LinkedHashMap::new));
    }

    public Result makeDealerResult(final Dealer dealer, final List<Player> players) {
        final List<Score> scores = players.stream()
            .map(Player::getScore)
            .collect(Collectors.toList());
        return Result.decide(dealer.getScore(), scores);
    }
}
