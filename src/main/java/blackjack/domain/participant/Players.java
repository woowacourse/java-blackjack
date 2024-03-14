package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.exception.InvalidPlayerCountException;
import java.util.List;

public class Players {
    private final List<Player> players;
    private int order = 0;

    public Players(List<Name> playerNames, Deck deck) {
        checkPlayersEmpty(playerNames);
        this.players = playerNames.stream()
                .map(playerName -> new Player(playerName, deck))
                .toList();
    }

    private void checkPlayersEmpty(List<Name> playerNames) {
        if (playerNames.isEmpty()) {
            throw new InvalidPlayerCountException();
        }
    }

    public boolean hasNext() {
        return order < players.size();
    }

    public void increaseOrder(PlayerAction playerAction) {
        if (playerAction.equals(PlayerAction.STAND) || !getPlayerAtOrder().canHit()) {
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
