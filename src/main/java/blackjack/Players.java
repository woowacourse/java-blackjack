package blackjack;

import blackjack.user.Player;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Players implements Iterable {
    private static final String NAME_DUPLICATION_EXCEPTION = "[ERROR] 중복된 플레이어 이름이 있습니다.";
    private final Queue<Player> players;

    private Players(List<String> playerNames) {
        List<String> copiedPlayerNames = new ArrayList<>(playerNames);
        validateDuplication(copiedPlayerNames);
        players = new LinkedList<>();
        for (String name : copiedPlayerNames) {
            players.add(Player.generate(name));
        }
    }

    private void validateDuplication(List<String> names) {
        Set<String> noDuplicatedName = new HashSet<>(names);
        if (names.size() != noDuplicatedName.size()) {
            throw new IllegalArgumentException(NAME_DUPLICATION_EXCEPTION);
        }
    }

    public static Players generateWithNames(List<String> playerNames) {
        return new Players(playerNames);
    }

    public Player turnPlayer() {
        return players.peek();
    }

    public void changeTurn() {
        players.add(players.remove());
    }

    public void drawInitialCards(Deck deck) {
        for (Player player : players) {
            player.drawInitialCards(deck);
        }
    }

    @Override
    public Iterator iterator() {
        return players.iterator();
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
