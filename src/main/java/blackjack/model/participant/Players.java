package blackjack.model.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        validate(players);
        this.players = List.copyOf(players);
    }

    public static Players from(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(Player.from(name));
        }

        return new Players(players);
    }

    private void validate(List<Player> players) {
        int playerSize = players.size();
        if (playerSize != Set.copyOf(players).size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
        if (playerSize > 6 || playerSize < 1) {
            throw new IllegalArgumentException("가능한 플레이어 슈는 1명 ~ 6명 입니다.");
        }
    }

    public void dealInitialHand(Dealer dealer) {
        for (Player player : players) {
            player.receiveHand(dealer.drawCard());
            player.receiveHand(dealer.drawCard());
        }
    }

    public List<Player> getPlayers() {
        return players.stream().toList();
    }
}
