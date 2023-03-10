package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int INITIAL_HAND_OUT_COUNT = 2;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> playersName) {
        validatePlayersName(playersName);
        List<Player> players = playersName.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    private static void validatePlayersName(List<String> playersName) {
        if (playersName.size() != new HashSet<>(playersName).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public void handInitialCards(Deck deck) {
        for (Player player : players) {
            handInitialCardsToPlayer(player, deck);
        }
    }

    private void handInitialCardsToPlayer(Player player, Deck deck) {
        for (int i = 0; i < INITIAL_HAND_OUT_COUNT; i++) {
            player.take(deck.draw());
        }
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
