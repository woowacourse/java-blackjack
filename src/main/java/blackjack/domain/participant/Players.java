package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames) {
        return new Players(new ArrayList<>(playerNames.stream()
                        .map(Player::of)
                        .collect(Collectors.toList())));
    }

    public void receive(CardDeck cardDeck, int eachCount) {
        for (Player player : players) {
            player.receive(cardDeck, eachCount);
        }
    }

    public Map<Player, Result> judgeResult(int score) {
        Map<Player, Result> result = new HashMap<>();
        for (Player player : players) {
            result.put(player, player.judgeResult(score));
        }
        return result;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
