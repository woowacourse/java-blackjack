package blackjack.domain.gamer;

import blackjack.domain.supplies.Hand;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {
    private static final String NAMES_DUPLICATE_ERROR = "플레이어 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names, Hand hand) {
        validateDuplicate(names);
        return new Players(names.stream()
                .map(name -> new Player(new Gamer(hand.copy()), name))
                .toList());
    }

    private static void validateDuplicate(List<String> names) {
        int distinctCount = new HashSet<>(names).size();
        if (names.size() != distinctCount) {
            throw new IllegalArgumentException(NAMES_DUPLICATE_ERROR);
        }
    }

    public void draw(List<Hand> playersCards) {
        int i = 0;
        for (Hand playerCards : playersCards) {
            players.get(i).draw(playerCards.getMyCards());
            i++;
        }
    }

    public List<String> getNames() {
        return players.stream().map(Player::getName).toList();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int getPlayersCount() {
        return players.size();
    }
}
