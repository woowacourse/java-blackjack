package blackjack.domain.participant;

import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private static final int MIN_PLAYERS_NUMBER = 1;
    private static final String PLAYER_COUNT_ERROR_MESSAGE = "플레이어 수는 " + MIN_PLAYERS_NUMBER + "명 이상이어야 합니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
        validateNumberOfPlayer(players);
    }

    private void validateNumberOfPlayer(List<Player> players) {
        if (players.size() < MIN_PLAYERS_NUMBER) {
            throw new IllegalArgumentException(PLAYER_COUNT_ERROR_MESSAGE);
        }
    }

    public void drawAtFirst(Deck deck) {
        players.forEach(player -> player.drawAtFirst(deck));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
