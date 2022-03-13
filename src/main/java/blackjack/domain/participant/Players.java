package blackjack.domain.participant;

import static blackjack.domain.participant.Participant.INITIAL_CARD_HAND;

import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public static Players of(List<String> playerNames) {
        return new Players(playerNames.stream()
                .map(Player::of)
                .collect(Collectors.toList()));
    }

    public void receive(CardDeck cardDeck) {
        for (Player player : players) {
            player.receive(cardDeck.distribute(INITIAL_CARD_HAND));
        }
    }

    public Map<Player, Boolean> judgeResult(int score) {
        Map<Player, Boolean> result = new HashMap<>();
        for (Player player : players) {
            result.put(player, player.isWinner(score));
        }
        return result;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
