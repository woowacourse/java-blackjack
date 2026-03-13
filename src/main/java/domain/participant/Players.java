package domain.participant;

import domain.Deck;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Players implements Iterable<Player> {
    private static final String ERROR_DUPLICATE_NAME = "플레이어 이름은 중복될 수 없습니다.";
    private static final String ERROR_PLAYER_COUNT = "참가할 플레이어의 수는 최대 7명입니다.";
    private static final int MAX_PLAYER_COUNT = 7;
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public static Players of(List<Player> players) {
        validate(players);
        return new Players(players);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    private static void validate(List<Player> players) {
        validateUniqueName(players);
        validatePlayerCount(players);
    }

    private static void validatePlayerCount(List<Player> players) {
        if (players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(ERROR_PLAYER_COUNT);
        }

    }

    private static void validateUniqueName(List<Player> players) {
        if (hasDuplicateName(players)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    private static boolean hasDuplicateName(List<Player> players) {
        Set<Player> uniquePlayers = new HashSet<>(players);

        return players.size() != uniquePlayers.size();
    }

    public void distributeCardsToAll(Deck deck, int cardCount) {
        for (Player player : players) {
            player.addAll(deck.draw(cardCount));
        }
    }
}
