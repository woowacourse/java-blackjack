package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.exception.InvalidPlayerCountException;
import java.util.List;

public class Players {
    private static final int MAX_PLAYER_COUNT = 8;

    private final List<Player> players;
    private int order = 0;

    public Players(List<Name> playerNames, Deck deck) {
        checkPlayersEmpty(playerNames);
        this.players = playerNames.stream()
                .map(playerName -> new Player(playerName, deck))
                .toList();
    }

    private void checkPlayersEmpty(List<Name> playerNames) {
        if (playerNames.isEmpty() || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new InvalidPlayerCountException();
        }
    }

    public boolean hasNext() {
        return order < players.size();
    }

    public void increaseOrder(PlayerAction playerAction) {
        if (playerAction.isStand() || getPlayerAtOrder().cannotHit()) {
            order++;
        }
    }

    public Player getPlayerAtOrder() {
        return players.get(order);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
