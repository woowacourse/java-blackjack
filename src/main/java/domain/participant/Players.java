package domain.participant;

import domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private static final String MAX_PLAYER_EXCEPTION = "플레이어는 5명까지만 입력해주세요.";
    private static final int MAX_PLAYER_SIZE = 5;

    private final List<Player> players;

    public Players (List<Player> players) {
        validateMaxPlayer(players);
        this.players = new ArrayList<>(players);
    }

    private void validateMaxPlayer(final List<Player> players) {
        if (players.size() > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(MAX_PLAYER_EXCEPTION);
        }
    }

    public void receiveInitialCards(final Deck deck) {
        for (Player player : players) {
            player.receiveCard(deck.draw(), deck.draw());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
