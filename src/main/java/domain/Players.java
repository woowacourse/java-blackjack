package domain;

import java.util.Collections;
import java.util.List;

public class Players {

    private static final int MAX_PLAYERS = 5;

    private final List<Player> players;

    private Players(List<Player> players) {
        validatePlayersNumber(players);
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    private void validatePlayersNumber(List<Player> players) {
        if (players.size() > MAX_PLAYERS) {
            throw new IllegalArgumentException("참여 가능한 플레이어는 최대 5명 입니다.");
        }
    }

    public void receiveCardFrom(Dealer dealer) {
        for (Player player : players) {
            player.receive(dealer.passCard());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
