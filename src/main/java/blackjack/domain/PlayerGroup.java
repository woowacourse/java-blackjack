package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PlayerGroup {
    private final List<Player> players;

    public PlayerGroup(List<Player> players) {
        this.players = players;
    }

    public void addTwoCards(Card firstCard, Card secondCard) {
        for (Player player : players) {
            player.addTwoCards(firstCard, secondCard);
        }
    }

    public Map<String, Match> getPlayerResult(int sum) {
        Map<String, Match> result = new LinkedHashMap<>();
        for (Player player : players) {
            Optional<Match> matchResult = Match.of(player.compareCardsSumTo(sum));
            matchResult.ifPresent(match -> result.put(player.getName(), match));
        }
        return result;
    }
}
