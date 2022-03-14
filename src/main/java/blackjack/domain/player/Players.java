package blackjack.domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;
    private int turn;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void nextTurn() {
        this.turn++;
    }

    public boolean hasNextTurn() {
        return this.turn < players.size();
    }

    public boolean isDealerBlackJack() {
        Player dealer = players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow();

        return dealer.isBlackJack();
    }

    public Player turnPlayer() {
        return this.players.get(turn);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findAny()
                .orElseThrow();
    }
}
