package domain.participant;

import domain.card.Deck;
import util.InputNameParser;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {

    private static final int MAX_PLAYERS = 8;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players fromString(String rawPlayerNames) {
        List<Name> playerNames = InputNameParser.parsePlayerNames(rawPlayerNames);

        validateInputNames(playerNames);

        List<Player> players = playerNames.stream().map(Player::new).toList();
        return new Players(players);
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public void drawInitialCards(Deck deck) {
        for (Player player : players) {
            player.drawInitialCards(deck.drawInitialCards());
        }
    }

    public void endGameImmediately() {
        for (Player player : players) {
            forceStay(player);
        }
    }

    private void forceStay(Player player) {
        if (player.isRunning()) {
            player.stay();
        }
    }

    private static void validateInputNames(List<Name> playerNames) {
        validateDuplicateNames(playerNames);
        validatePlayerCounts(playerNames);

    }

    private static void validateDuplicateNames(List<Name> playerNames) {
        if (playerNames.size() != (new HashSet<>(playerNames)).size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복되지 않아야 합니다.");
        }
    }

    private static void validatePlayerCounts(List<Name> playerNames) {
        if (playerNames.isEmpty() || playerNames.size() > MAX_PLAYERS) {
            throw new IllegalArgumentException(String.format("플레이어의 수는 1명 이상 %s명 이하여야 합니다.", MAX_PLAYERS));
        }
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
