package domain.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackResultMaker {

    public Map<Participant, Result> makeResult(final Dealer dealer, final List<Player> players) {
        final Map<Participant, Result> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, Result.decide(player.getScore(), dealer.getScore()));
        }
        final List<Score> scores = players.stream()
            .map(Participant::getScore)
            .collect(Collectors.toList());
        results.put(dealer, Result.decide(dealer.getScore(), scores));
        return results;
    }
}
