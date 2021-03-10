package blackjack.domain.participant;

import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final int MIN_PLAYERS_NUMBER = 1;
    private static final String PLAYER_COUNT_ERROR_MESSAGE = "플레이어 수는 " + MIN_PLAYERS_NUMBER + "명 이상이어야 합니다.";

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validateNumberOfPlayer(new ArrayList<>(playerNames));
        players = makePlayers(playerNames);
    }

    private void validateNumberOfPlayer(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYERS_NUMBER) {
            throw new IllegalArgumentException(PLAYER_COUNT_ERROR_MESSAGE);
        }
    }

    private List<Player> makePlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void drawAtFirst(Deck deck) {
        players.forEach(player -> player.drawAtFirst(deck));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
