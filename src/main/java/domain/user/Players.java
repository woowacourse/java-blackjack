package domain.user;

import domain.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validateDuplicatedName(players);
        this.players = players;
    }

    void putStartCards(Deck deck) {
        for (Player player : players) {
            player.addCard(deck.getNewCard());
            player.addCard(deck.getNewCard());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private void validateDuplicatedName(List<Player> players) {
        List<Player> distinctPlayers = new ArrayList<>();
        for (Player player : players) {
            throwExceptionIfContains(distinctPlayers, player);
            distinctPlayers.add(player);
        }
    }

    private static void throwExceptionIfContains(List<Player> distinctPlayers, Player player) {
        if (distinctPlayers.contains(player)) {
            throw new IllegalArgumentException(
                    "중복된 이름은 입력할 수 없습니다: %s".formatted(player.getNameValue()));
        }
    }
}
