package domain.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultMaker {

    public Map<Player, Result> makeParticipantsResult(final Dealer dealer, final List<Player> players) {
        Map<Player, Result> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, Result.decide(player.getScore(), dealer.getScore()));
        }
        return results;
    }

    public Result makeDealerResult(final Dealer dealer, final List<Player> players) {
        final List<Score> scores = players.stream()
            .map(Player::getScore)
            .collect(Collectors.toList());
        return Result.decide(dealer.getScore(), scores);
    }
}
