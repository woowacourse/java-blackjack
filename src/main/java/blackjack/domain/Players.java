package blackjack.domain;

import blackjack.domain.user.Player;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
    private static final String NAME_DUPLICATION_EXCEPTION = "[ERROR] 중복된 플레이어 이름이 있습니다.";
    private final List<Player> players;

    private Players(List<String> playerNames) {
        List<String> copiedPlayerNames = new ArrayList<>(playerNames);
        validateDuplication(copiedPlayerNames);
        players = new ArrayList<>();
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

    public void drawInitialCards(Deck deck) {
        for (Player player : players) {
            player.drawInitialCards(deck);
        }
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator iterator() {
        return players.iterator();
    }
}
